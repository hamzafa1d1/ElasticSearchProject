from fastapi import FastAPI, File, UploadFile

from ImageEmbedding.EmbeddingsGenerator import EmbeddingsGenerator

app = FastAPI()


@app.post("/uploadfile")
async def upload_file(file: UploadFile):
    
    image_data = await file.read()
    return {"imageEmbedding": EmbeddingsGenerator().generateFromImage(image_data)}
