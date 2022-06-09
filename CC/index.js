const express = require('express');
const app = express();
const bodyParser = require('body-parser'); 
const RouteUser = require('./routes/User');
const mongoose = require('mongoose');
const dotenv = require('dotenv');

app.use(bodyParser.json())

app.use(express.urlencoded({extended: true}))

//connect to mongodb
dotenv.config();
mongoose 
 .connect(process.env.DB_CONNECT, {
        useNewUrlParser: true,
        useUnifiedTopology: true
      })   
 .then(() => console.log("Database connected!"))
 .catch(err => console.log(err));

  
// app.use(bodyParser.json());
app.use('/', RouteUser);    


app.listen(3005, () => {
    console.log("Server running port 3005");
})
