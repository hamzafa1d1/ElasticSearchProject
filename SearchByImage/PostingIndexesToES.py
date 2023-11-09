from uuid import uuid1
from elasticsearch import Elasticsearch
import csv
from EmbeddingsGenerator import EmbeddingsGenerator
from ImageEmbedding.ImageCleaning import ImageCleaning

class PostingIndexesToES:
    # Initialize Elasticsearch connection
    es = Elasticsearch([{'host': '127.0.0.1', 'port': 9200}])
    data_directory = r"C:\Users\hamza\Desktop\supcom projects\donnee tp elasticsearch\photo_metadata_ex.csv"
    index_name = 'flickrphotos'
    def generateMapping(self):
        return {
            "settings": {
                "number_of_shards": 1,
                "number_of_replicas": 0,
            },
            "mappings": {
                "dynamic": False,
                "properties": {
                    "id": {"type": "keyword"},
                    "url": {"type": "text"},
                    "vector": {
                        "type": "elastiknn_dense_float_vector",
                        "elastiknn": {
                            "dims": 4096,
                            "similarity": "L2",  # Use "l2" for Euclidean distance
                            "model": "lsh",
                            "L": 99,
                            "k": 1,
                            "w": 3
                        }
                    },
                    "tags": {"type": "text"}
                }
            }
        }

    def createIndex(self):
        #make sure there is no previous index
        if(self.es.indices.exists(index = self.index_name)) :
            self.es.indices.delete(index= self.index_name)
        self.es.indices.create(index= self.index_name, body= self.generateMapping())

    def saveIndexToEs(self):
        self.createIndex()
        with open(self.data_directory, 'r', encoding='utf-8') as file:
            i =0
            reader = csv.reader(file)
            for row in reader:
                if(i ==0):
                    #skip the header
                    i = i + 1
                    continue
                photo_url = ImageCleaning().generateImageUrl(row)
                if ImageCleaning().checkImageIsNotCorrupted(photo_url):
                    vector_list = EmbeddingsGenerator().generateFromUrl(photo_url)
                    tags = row[3]
                    doc = {
                        "id": uuid1(),
                        "url": photo_url,
                        "vector": vector_list,
                        "tags": tags  # Include the 'tags' field in the document
                    }
                    self.es.index(index= self.index_name, body=doc)
                else:
                    print(f"Failed to fetch image from {photo_url}")

postingIndexesToEs = PostingIndexesToES()
postingIndexesToEs.saveIndexToEs()