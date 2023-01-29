import React, {useState} from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import axios from 'axios'

const WALLET_API_BASE_URL = "http://localhost:8080/wallet";

const Wallet = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const uname = location.state.username;
    const [amt, setAmt] = useState('');

    const validate = () => {
        if (amt.trim().length > 0 && !isNaN(+amt)) {return true}
        return false
    }

    const handleBalance = (e) => {
        e.preventDefault();
        axios.get(WALLET_API_BASE_URL+"/balance", {
            params:{
                username: uname
            }
        })
            .then(response => {
              alert("Your current balance is: " + response.data)
            })
            .catch(error => {
                console.log(error);
                alert("Error occured");
            });
    }

    const handleAdd = (e) => {
        e.preventDefault();
        let isValid = validate();
        if (isValid) {
        axios.put(WALLET_API_BASE_URL+"/add/" + amt, null, {
            params:{
                username:uname
            }
        })
            .then(response => {
              alert(response.data.message)
              setAmt('');
            })
            .catch(error => {
                console.log(error);
                alert("Error occured");
            });
        }
        else{
            alert("Invalid input")
        }
    }
    const handleWithdraw = (e) => {
        e.preventDefault();
        let isValid = validate();
        if (isValid) {
        axios.put(WALLET_API_BASE_URL+"/withdraw/" + amt, null, {
            params:{
                username: uname
            }
        })
            .then(response => {
              alert(response.data.message)
              setAmt('');
            })
            .catch(error => {
                console.log(error);
                alert("Error occured");
            });
        }
        else{
            alert("Invalid input")
        }
    }
    const handleHistory = (e) => {
        e.preventDefault();
        axios.get(WALLET_API_BASE_URL+"/history", {
            params:{
                username: uname
            }
        })
            .then(response => {
              console.log(response)
              alert("Transactions: " + response.data)
            })
            .catch(error => {
                console.log(error);
                alert("Error occured");
            });
    }

    const handleLogout = (e) => {
        e.preventDefault();
        navigate("/login")
    }

    const handleDelete = (e) => {
        e.preventDefault();
        axios.delete(WALLET_API_BASE_URL+"/delete", {
            params:{
                username: uname
            }
        })
            .then(response => {
              alert(response.data.message)
              navigate("/login")
            })
            .catch(error => {
                console.log(error);
                alert("Error occured");
            });
    }

    return (
      <div>
        <h1>Hi {uname}! Welcome to your wallet</h1>
          <div>
            <input
              type="number"
              precision = {2}
              min = {0}
              name="amount"
              placeholder="amount"
              value={amt}
              onChange={(e) =>
                {
                    setAmt(e.target.value);      
                }}
            /> 
          </div>

          <button onClick={handleAdd}>Add</button>
          <button onClick={handleWithdraw}>Withdraw</button>
          <button onClick={handleBalance}>Balance</button>
          <button onClick={handleHistory}>History</button>
          <p></p>
        <button onClick={handleLogout}>Logout</button>
        <button onClick={handleDelete}>Delete account</button>
      </div>
    );
};
export default Wallet;
