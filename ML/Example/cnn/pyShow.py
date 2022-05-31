# -*- coding: utf-8 -*-
"""
Created on Tue Jul 20 14:30:12 2021

@author: Andre
"""

import glob
import matplotlib.pyplot as plt
#%matplotlib inline

def tampilkan_6_kali_5_gambar_pada_alamat(ini):
  a = glob.glob(ini)

  w=10
  h=10
  fig=plt.figure(figsize=(8, 8))
  columns = 6
  rows = 5
  for i in range(1, columns*rows +1):
      img = plt.imread(a[i])
      fig.add_subplot(rows, columns, i)
      plt.imshow(img)
  plt.show()


tampilkan_6_kali_5_gambar_pada_alamat("dataset/test/cat/*")
#tampilkan_6_kali_5_gambar_pada_alamat("dataset/test/dog/*")
#tampilkan_6_kali_5_gambar_pada_alamat("dataset/test/tiger/*")
