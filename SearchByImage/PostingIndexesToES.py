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
            "mappings": {
                "properties": {
                    "id": {"type": "keyword"},
                    "url": {"type": "text"},
                    "vector": {
                        "type": "dense_vector",
                        "dims": 2000,
                        "index": True,
                        "similarity": "l2_norm"
                    },
                    "tags": {"type": "text"},
                    "title": {"type": "text"}
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
                    title = row[2]
                    doc = {
                        "id": uuid1(),
                        "url": photo_url,
                        "vector": vector_list,
                        "tags": tags,  # Include the 'tags' field in the document
                        "title": title
                    }
                    self.es.index(index= self.index_name, body=doc)
                    self.es.search()
                else:
                    print(f"Failed to fetch image from {photo_url}")

postingIndexesToEs = PostingIndexesToES()
postingIndexesToEs.saveIndexToEs()