# -*- coding: utf-8 -*-
"""
Created on Sat Jul 24 15:23:40 2021

@author: Andre
"""
import matplotlib.pyplot as plt

a = "dataset/test/dog/n02085620_3742.jpg"
b = "dataset/test/cat/00000136_014.jpg"
c = "dataset/test/tiger/002884.jpg"


def tampilkan_(ini):
  fig = plt.figure(figsize=(2, 2))
  img = plt.imread(ini)
  plt.imshow(img)
  plt.show()

tampilkan_(a)
tampilkan_(b)
tampilkan_(c)
