import { useEffect, useState } from "react";
import { web3 } from "../contracts/CallContract";
import Web3 from "web3";

export default function Blocktest() {
  const [password, setPassword] = useState("");
  const [userAccount, setUserAccount] = useState([]);
  const getpassword = (e) => {
    setPassword(e.target.value);
  };
  const CreateAccount = async (e) => {
    e.preventDefault();
    initWeb3();
    console.log(web3);
    // let accounts = web3.eth.getAccounts();
    let accounts = await web3.eth.personal.newAccount(password);
    console.log(accounts);
    // 지금까지 생성된 계정 리스트로 쭉 나옴
    // console.log(web3.eth.personal.getAccounts());
    setUserAccount(accounts);

    // let balance = web3.eth.getBalance(accounts[0]);
    // console.log(balance);
  };

  const initWeb3 = async () => {
    if (window.ethereum) {
      console.log("Recent mode");
      window.web3 = new Web3(window.ethereum);
      try {
        // Request account access if needed
        await window.ethereum.enable();
        // Acccounts now exposed
        // web3.eth.sendTransaction({
        /* ... */
        // };
      } catch (error) {
        // User denied account access...
        console.log("User denied account access error:"`${error}`);
      }
    }
    // Legacy dapp browsers...
    else if (window.web3) {
      console.log("Legacy mode");
      window.web3 = new Web3(web3.currentProvider);
      // Acccounts always exposed
      // web3.eth.sendTransaction({
      //   /* ... */
      // });
    }
    // Non-dapp browsers...
    else {
      console.log(
        "Non-Ethereum browser detected. You should consider trying MetaMask!"
      );
    }
  };
  return (
    <>
      <form action="">
        <input
          type="text"
          placeholder="지갑 패스워드 입력"
          onChange={getpassword}
        />
        <button onClick={CreateAccount}>회원가입 & 계정 생성</button>
        <div>환영합니다! 당신의 계정은 {userAccount} 입니다.</div>
      </form>
    </>
  );
}
