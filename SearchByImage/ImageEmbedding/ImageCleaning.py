import os
import csv
import requests
class ImageCleaning:

    csv_file_path = r'C:\Users\HamzaFaidi\Desktop\Supcom Things\ter_indexation_p1_atelier1\photo_metadata_ex.csv'
    output_directory = r'C:\Users\HamzaFaidi\Desktop\Supcom Things\ter_indexation_p1_atelier1'
    new_csv_file_path = r'C:\Users\HamzaFaidi\Desktop\Supcom Things\ter_indexation_p1_atelier1\cleanedFile.csv'
    def checkImageIsNotCorrupted(self, url):
        response = requests.get(url)
        if response.status_code == 200:
            return True
        else:
            return False

    def cleaning(self):

        # Create the output directory if it doesn't exist
        os.makedirs(self.output_directory, exist_ok=True)

        # List to store non-corrupted URLs
        non_corrupted_urls = []

        # Open and read the CSV file
        with open(self.csv_file_path, 'r', newline='') as csvfile:
            i = 0
            reader = csv.reader(csvfile)
            for row in reader:
                #skip first line containing column desc
                if(i ==0):
                    i+=1
                    continue

                # Download the image
                url = self.generateImageUrl(row)
                if(self.checkImageIsNotCorrupted(url)):
                    non_corrupted_urls.append(url)

        self.generateCSV(non_corrupted_urls)

    def generateCSV(self, non_corrupted_urls):
        # Create a new CSV file with non-corrupted URLs
        with open(self.new_csv_file_path, 'w', newline='') as new_csvfile:
            writer = csv.writer(new_csvfile)
            writer.writerow(['URL'])  # Write the header
            for url in non_corrupted_urls:
                writer.writerow([url])
        print(f"Corrupted images have been deleted. Non-corrupted URLs saved to '{self.new_csv_file_path}'.")

    def generateImageUrl(self, row):
        return "http://farm" + str(row[12]) + ".staticflickr.com/" + str(row[11]) + "/" + str(row[0]) + "_" + str(
            row[10]) + ".jpg"

## running the cleaning script
cleaningInstance = ImageCleaning()
cleaningInstance.cleaning()