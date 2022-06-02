from tensorflow.keras.preprocessing import image
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Activation
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.metrics import categorical_accuracy
import glob

def switch_dict_key_values(this_dict):
    return dict((v,k) for k,v in this_dict.items())

nama_train_data = {0: 'cat', 1: 'dog', 2: 'tiger'}


size_ = 64

model = Sequential()

model.add(Conv2D(16, (3, 3), padding="valid", input_shape = (size_, size_, 3), activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))

model.add(Conv2D(32, (3, 3), padding="same", activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Conv2D(32, (3, 3), padding="same", activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))

model.add(Conv2D(64, (3, 3), padding="same", activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Conv2D(64, (3, 3), padding="same", activation = 'relu'))
model.add(MaxPooling2D(pool_size = (2, 2)))
model.add(Flatten())


model.add(Dense(units = 8, activation = 'relu'))
model.add(Dense(units = 3, activation = 'softmax'))

model.compile(optimizer = 'sgd', loss = 'categorical_crossentropy', metrics = [categorical_accuracy])
model.summary()

model.load_weights('9.h5')
''' 
data_anjing = glob.glob("dataset/test/cat/*")
  
for path in data_anjing:
    img = image.load_img(path, target_size = (size_, size_))
    img = image.img_to_array(img)
    img = np.expand_dims(img, axis = 0)
    hasil = model.predict_classes(img)
    print(nama_train_data[hasil[0]]) 
     
'''
def fungsi(lokasi_file_input):
    lokasi_file = lokasi_file_input
    #img = image.load_img('dataset/test/tiger/002887.jpg', target_size = (size_, size_))
    img = image.load_img(lokasi_file, target_size = (size_, size_))
    img = image.img_to_array(img)
    img = np.expand_dims(img, axis = 0)
    
    hasil = model.predict_classes(img)
    print(nama_train_data[hasil[0]])
    '''
    
    '''
a = "dataset/test/tiger/santai1.jpg"
fungsi("dataset/test/tiger/santai1.jpg")