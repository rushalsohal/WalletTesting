import React from 'react'
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const WALLET_API_BASE_URL = "http://localhost:8080/wallet";

function Login() {
    const navigate = useNavigate();
    const [uname, setUname] = useState('');
    const [pwd, setPwd] = useState('');
   // const [authenticated, setAuthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated")|| false));

    const validate = () => {
        if (uname.trim().length > 0 && pwd.trim().length > 0) {return true}
        return false
    }

    const handleRegister = (e) => {
        e.preventDefault();
       let isValid = validate();
    if(isValid)
    {
        axios.post(WALLET_API_BASE_URL+"/register",
        {
        username: uname,
        password: pwd
        })
            .then(response => {
              //  console.log(response);
                if(response.data.errorCode === 1){
                    alert("Username already taken! Try another");
                    setUname('');
                    setPwd('');
                }
                else{
                    alert("Registation Successful");
                    setUname('');
                    setPwd('');
                }
            })
            .catch(error => {
                console.log(error);
                alert("Registation Failed");
            });
    }
    else{
        alert("Invalid input")
    }
    }

    const handleLogin = (e) => {
        e.preventDefault();
        let isValid = validate();

        if (isValid) {
        axios.post(WALLET_API_BASE_URL+"/login",
        {
        username: uname,
        password: pwd
        })
            .then(response => {
                if(response.data.errorCode === 1){
                    alert("Login Failed! Invalid credentials.");
                    setUname('');
                    setPwd('');
                }
                else{
                 //   setAuthenticated(true)
                 //   localStorage.setItem("authenticated", true);
                    alert("Login successful");
                    setUname('');
                    setPwd('');
                    navigate("/wallet", {
                        state:{
                            username: uname
                        }
                    });
                }
            })
            .catch(error => {
                console.log(error);
                alert("Login failed! Error occured");
            });
        }
        else{
            alert("Invalid input")
        }
    }

    return (
        <>
        <h1>Welcome</h1>
          <div>
            <input
              type="text"
              name="username"
              placeholder="username"
              value={uname}
             onChange={(e) =>
                {
                    setUname(e.target.value);      
                }}
            /> 
          </div>

          <div>
            <input
              type="text"
              name="password"
              placeholder="password"
              value={pwd}
             onChange={(e) =>
                {
                    setPwd(e.target.value);      
                }}
            />
          </div>
          <button onClick={handleLogin}>Login</button>
          <button onClick={handleRegister}>Register</button>

      </>
    )
}
export default Login;