from tensorflow.keras.preprocessing import image
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Activation
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.metrics import categorical_accuracy
import glob

def switch_dict_key_values(this_dict):
    return dict((v,k) for k,v in this_dict.items())

nama_train_data = {0: 'chill', 1: 'hunting'}

size_ = 64

model = Sequential()

model.add(Conv2D(64, (3, 3), input_shape = (size_, size_, 3), activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Conv2D(32, (3, 3), activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Conv2D(16, (3, 3), activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Flatten())


model.add(Dense(units = 8, activation = 'relu'))
model.add(Dense(units = 1, activation = 'sigmoid'))

model.compile(optimizer = 'sgd', loss = 'binary_crossentropy', metrics = ['accuracy'])
model.summary()

model.load_weights('9.h5')
'''  
data_pose = glob.glob("dataset/test/chill/*")

for path in data_pose:
    img = image.load_img(path, target_size = (size_, size_))
    img = image.img_to_array(img)
    img = np.expand_dims(img, axis = 0)
    hasil = model.predict_classes(img)
    print(nama_train_data[hasil[0]]) 

'''

#img = image.load_img('dataset/test/tiger/002887.jpg', target_size = (size_, size_))
img = image.load_img('dataset/test/chill/chill_original_santai2.jpg_cdaed466-794f-407f-ab8e-7fd6c2aa7dc2.jpg', target_size = (size_, size_))
img = image.img_to_array(img)
img = np.expand_dims(img, axis = 0)

    
hasil = model.predict_classes(img)
#print(nama_train_data[hasil[0]])
print(nama_train_data[hasil[0][0]])
'''

'''