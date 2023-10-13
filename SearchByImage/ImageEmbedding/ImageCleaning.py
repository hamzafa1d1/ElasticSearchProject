import os
import csv
import requests

class ImageCleaning:

    csv_file_path = 'corrupted_images.csv'
    output_directory = 'downloaded_images'

    def is_image_corrupted(image_path):
        try:
            # Check if the image file can be opened without errors
            with open(image_path, 'rb') as img_file:
                _ = img_file.read()
            return False
        except Exception as e:
            return True

    def download_image(url, output_dir):
        response = requests.get(url)
        if response.status_code == 200:
            # Extract the image filename from the URL
            filename = os.path.basename(url)
            output_path = os.path.join(output_dir, filename)
            with open(output_path, 'wb') as img_file:
                img_file.write(response.content)
            return output_path
        else:
            return None

    def cleaning(self):

        # Create the output directory if it doesn't exist
        os.makedirs(self.output_directory, exist_ok=True)

        # List to store non-corrupted URLs
        non_corrupted_urls = []

        # Open and read the CSV file
        with open(self.csv_file_path, 'r', newline='') as csvfile:
            reader = csv.reader(csvfile)
            for row in reader:
                url = row[0]
                # Download the image
                image_path = self.download_image(url, self.output_directory)
                if image_path:
                    # Check if the downloaded image is corrupted
                    if not self.is_image_corrupted(image_path):
                        non_corrupted_urls.append(url)
                    else:
                        # Delete the corrupted image
                        os.remove(image_path)

    def generateCSV(self, non_corrupted_urls):
        # Create a new CSV file with non-corrupted URLs
        new_csv_file_path = 'non_corrupted_images.csv'
        with open(new_csv_file_path, 'w', newline='') as new_csvfile:
            writer = csv.writer(new_csvfile)
            writer.writerow(['URL'])  # Write the header
            for url in non_corrupted_urls:
                writer.writerow([url])

        print(f"Corrupted images have been deleted. Non-corrupted URLs saved to '{new_csv_file_path}'.")