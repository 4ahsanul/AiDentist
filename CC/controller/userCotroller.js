const dotenv = require('dotenv');
const User = require('../models/userModel');
const bcryptjs = require('bcryptjs');
const jsonwebtoken = require('jsonwebtoken');
const {check} = require('express-validator')
const emailValidator = require('email-validator');

dotenv.config();

// Register User

exports.DaftarUser = async (req, res) => {
    try {
        const { name, email, password } = req.body;

        // User Validation
        if (!password || password.length < 8) {
            return res
            .status(400)
            .send("Password must be at least 8 characters long.");
        }

        const userExits = await User.findOne({ email }).exec();
        if (userExits) {
            return res.status(400).send("Email is already");
        }

        if (!name) {
            return res
            .status(400)
            .send("Nama tidak boleh kosong");
        }


        if (!email) {
            return res
            .status(404)
            .send("Email tidak boleh kosong");
        }

        if (!emailValidator.validate(email)){
            return res
            .status(404)
            .send("Harus memakai tanda @")
        }

        //Hash Password
        const haasPassword = await bcryptjs.hash(password, 10);

        //User Create
        const newUser = new User({
            name: name,
            email: email,
            password: haasPassword
        });

        const user = await newUser.save();
        return res.status(201).json({
            messege: 'User Create',
            data: user
        });
    } catch (error) {
        console.log(error);
    }
} 

// Login User
exports.LoginUser = async (req, res) => {
    const { email, password } = req.body;

    //Validation
    if(!email) {
        return res
        .status(404)
        .send('Email tidak boleh kosong')
    }

    if (!password) {
        return res
        .status(404)
        .send('Password tidak boleh kosong')
    }

    if (!emailValidator.validate(email)){
        return res
        .status(404)
        .send("Harus memakai tanda '@'")
    }

    const emailLogin = await User.findOne({ email: email });
    if (emailLogin) {
        //Proces success in email
       const passwordLogin = await bcryptjs.compare(password, emailLogin.password)
       if(passwordLogin) {
           //Proces success in password
           const data = {
               id: emailLogin._id
           };
           const token = jsonwebtoken.sign(data, process.env.JWT_SECRET)
           return res.status(200).json({
                messege: 'berhasil',
                token: token
            });
       } else {
           return res.status(400).json({
               messege: 'Password salah'
           })
       }
    } else {
        return res.status(400).json({
            messege: 'Username tidak tersedia'
        })
    }

    
}