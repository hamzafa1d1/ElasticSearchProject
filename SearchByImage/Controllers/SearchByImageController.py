from fastapi import FastAPI, File, UploadFile

app = FastAPI()


@app.post("/uploadfile/")
async def upload_file(file: UploadFile):
    
    image_data = await file.read()
    return {"filename": file.filename}
