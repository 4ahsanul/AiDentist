import Augmentor
import os

def perbanyak_(ini, sebanyak_ini):
  source_dir = ini
  output_dir = "."
  p = Augmentor.Pipeline(source_directory=source_dir, output_directory=output_dir)
  p.random_distortion(probability=1, grid_width=4, grid_height=4, magnitude=1)
  p.rotate(probability=0.7, max_left_rotation=13, max_right_rotation=13)
  p.zoom_random(probability=0.5, percentage_area=0.9)
  p.crop_random(probability=0.6, percentage_area=0.9)
  p.resize(probability=1.0, width=64, height=64)

  p.sample(sebanyak_ini)
  
'''
perbanyak_("dataset/test/5", 30)
perbanyak_("dataset/test/s", 30)
perbanyak_("dataset/train/5", 270)
'''
perbanyak_("dataset/train/chill", 2000)
perbanyak_("dataset/train/hunting", 2000)
perbanyak_("dataset/test/chill", 100)
perbanyak_("dataset/test/hunting", 100)
