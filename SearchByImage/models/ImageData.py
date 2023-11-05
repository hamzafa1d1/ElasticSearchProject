class ImageData:
    def __init__(self, ImageUrl, Embedding):
        self.ImageUrl = ImageUrl
        self.Embedding = Embedding

    def to_json(self):
        return {
            'ImageUrl': self.ImageUrl,
            'Embedding': self.Embedding
        }