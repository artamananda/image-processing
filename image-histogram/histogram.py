from PIL import Image
import matplotlib.pyplot as plt
import numpy as np

a = np.array(Image.open(r"C:\Users\Arta's Windows\Pictures\Screenshots\input.jpg"))
x = np.ravel(a)

plt.hist(x)
plt.show() 