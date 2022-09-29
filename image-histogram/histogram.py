from matplotlib import pyplot as plt
from PIL import Image
import numpy as np
import os

img=Image.imread('red_image.png')
print('The Shape of the image is:',img.shape)
print('The image as array is:')
print(img)

# def main():
#     try:
#         #Relative Path
#         #Image on which we want to paste
#         img = Image.open("input.jpg") 
          
#         #Relative Path
#         #Image which we want to paste
#         img2 = Image.open("input2.jpg") 
#         img.paste(img2, (50, 50))
          
#         #Saved in the same relative location
#         img.save("output.jpg")
          
#     except IOError:
#         pass
  
# if __name__ == "__main__":
#     main()

# # Creating dataset
# a = np.array([22, 87, 5, 43, 56,
# 			73, 55, 54, 11,
# 			20, 51, 5, 79, 31,
# 			27])

# # Creating histogram
# fig, ax = plt.subplots(figsize =(10, 7))
# ax.hist(a, bins = [0, 25, 50, 75, 100])

# # Show plot
# plt.show()
