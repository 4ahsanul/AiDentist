from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Activation
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.metrics import categorical_accuracy

def switch_dict_key_values(this_dict):
    return dict((v,k) for k,v in this_dict.items())

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

train = ImageDataGenerator(rescale = 1./255)
test = ImageDataGenerator(rescale = 1./255)

train_data = train.flow_from_directory('dataset/train', target_size = (size_, size_), 
                                       batch_size = 32, class_mode = 'binary')
test_data = test.flow_from_directory('dataset/test', target_size = (size_, size_), 
                                     batch_size = 32, class_mode = 'binary')




nama_train_data = switch_dict_key_values(train_data.class_indices)
print(nama_train_data)
'''   
for i in range(0, 10):
    model.fit_generator(train_data, steps_per_epoch = 120,
                        epochs = 10, validation_data = test_data, 
                        validation_steps = 100)
    model.save_weights(str(i)+'.h5')
 
'''   
'''
for i in range(0, 10):
  #model.fit_generator(train_data, steps_per_epoch = 100, epochs = 1, validation_data = test_data, validation_steps = 100)
  model.fit_generator(train_data, epochs = 1, validation_data = test_data)
  model.save_weights(str(i)+'.h5')
'''
  