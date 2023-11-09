import numpy as np
import requests
from PIL import Image
from io import BytesIO
from keras.applications.vgg16 import VGG16, preprocess_input
from keras.preprocessing import image
from keras.models import Model
class EmbeddingsGenerator:
    def generateFromUrl(self, imageUrl):
        #download the image
        #calculate the embedding of the image and return it
        base_model = VGG16(weights='imagenet')

        # Create a new model that excludes the top classification layers
        model = Model(inputs=base_model.input, outputs=base_model.get_layer('fc2').output)

        try:
            # Fetch the image from the URL
            response = requests.get(imageUrl)
            response.raise_for_status()
            img_data = response.content

            # Load and preprocess the image
            img = Image.open(BytesIO(img_data))
            img = img.resize((224, 224))  # Resize to VGG16 input size
            img = image.img_to_array(img)
            img = np.expand_dims(img, axis=0)
            img = preprocess_input(img)

            # Get the embedding for the image
            embedding = model.predict(img)

            # Return the embedding as a numpy array
            return embedding.tolist()[0][:2000]

        except Exception as e:
            print(f"Error: {e}")
            return None
    def generateFromImage(self, image):
        # Load the VGG16 model pre-trained on ImageNet data
        base_model = VGG16(weights='imagenet')

        # Create a new model that excludes the top classification layers
        model = Model(inputs=base_model.input, outputs=base_model.get_layer('fc2').output)

        try:
            # Load the image from the image data using PIL
            img = Image.open(image)
            img = img.resize((224, 224))

            # Convert the PIL image to a NumPy array
            img = np.array(img)
            img = np.expand_dims(img, axis=0)
            img = preprocess_input(img)

            # Get the embedding for the image
            embedding = model.predict(img)

            # Return the embedding as a numpy array
            return embedding.tolist()[0][:2000]

        except Exception as e:
            print(f"Error: {e}")
            return None


### testing
# imageUrl = "https://t4.ftcdn.net/jpg/00/97/58/97/360_F_97589769_t45CqXyzjz0KXwoBZT9PRaWGHRk5hQqQ.jpg"
# print(EmbeddingsGenerator().generateFromUrl(imageUrl))