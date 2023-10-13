import os
import csv
import requests

# we use this class to clean the dataset that we have
# for flicker photos and keep only rows with non corrupted images

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
        rows_with_non_corrupted_urls = []
        header = []

        # Open and read the CSV file
        with open(self.csv_file_path, 'r', newline='') as csvfile:
            i = 0
            reader = csv.reader(csvfile)
            for row in reader:
                #skip first line containing column desc
                if(i ==0):
                    i+=1
                    header = row
                    header.append('Url')
                    continue

                # Download the image
                url = self.generateImageUrl(row)
                if(self.checkImageIsNotCorrupted(url)):
                    row.append(url)
                    rows_with_non_corrupted_urls.append(row)

        self.generateCSV(rows_with_non_corrupted_urls, header)

    def generateCSV(self, rows_with_non_corrupted_urls, header):
        # Create a new CSV file with non-corrupted URLs
        with open(self.new_csv_file_path, 'w', newline='') as new_csvfile:
            writer = csv.writer(new_csvfile)
            writer.writerow(header)  # Write the header
            for row in rows_with_non_corrupted_urls:
                writer.writerow(row)
        print(f"Corrupted images have been deleted. Non-corrupted URLs saved to '{self.new_csv_file_path}'.")

    def generateImageUrl(self, row):
        return "http://farm" + str(row[12]) + ".staticflickr.com/" + str(row[11]) + "/" + str(row[0]) + "_" + str(
            row[10]) + ".jpg"

## running the cleaning script
cleaningInstance = ImageCleaning()
cleaningInstance.cleaning()