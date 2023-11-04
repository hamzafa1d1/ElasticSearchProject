from fastapi import FastAPI, UploadFile

from EmbeddingsGenerator import EmbeddingsGenerator

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}
@app.post("/uploadfile")
async def upload_file(file: UploadFile):

    image_data = await file.read()
    return {"imageEmbedding": EmbeddingsGenerator().generateFromImage(image_data)}
