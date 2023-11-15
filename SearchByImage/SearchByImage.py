
from fastapi import FastAPI, UploadFile,File
from starlette.responses import JSONResponse

from EmbeddingsGenerator import EmbeddingsGenerator

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}
@app.post("/uploadfile")
async def upload_file(file: UploadFile = File(...)):

    embedding_result = EmbeddingsGenerator().generateFromImage(file.file)

    return JSONResponse(content={"Embedding": embedding_result}, status_code=200)
