import pandas as pd
import requests
import tensorflow as tf
from tensorflow.keras.applications.inception_v3 import InceptionV3, preprocess_input
from tensorflow.keras.models import Model

from ImageEmbedding import ImageCleaning

ImageCleaning cleaner = ImageCleaning()
url = cleaner.cleaning()

# Load the CSV file containing image URLs
df = pd.read_csv(url)

# Function to download images from URLs
def download_image(url):
    response = requests.get(url)
    return response.content

# Create a new column with image data
df['image_data'] = df['URL'].apply(download_image)

# Load pre-trained InceptionV3 model
base_model = InceptionV3(weights='imagenet')
model = Model(inputs=base_model.input, outputs=base_model.layers[-2].output)

# Function to extract image embeddings
def extract_embedding(image_data):
    img = tf.image.decode_image(image_data)
    img = preprocess_input(img)
    img = tf.image.resize(img, (299, 299))  # InceptionV3 input size
    img = tf.expand_dims(img, axis=0)  # Add batch dimension
    embedding = model.predict(img)
    return embedding

# Create a new column with image embeddings
df['image_embedding'] = df['image_data'].apply(extract_embedding)

# Save the DataFrame with embeddings to a CSV file
df.to_csv('image_embeddings.csv', index=False)

