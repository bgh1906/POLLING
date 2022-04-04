import { useEffect, useState } from "react";
import { web3 } from "../contracts/CallContract";

export default function Blocktest() {
  const [password, setPassword] = useState("");
  const [userAccount, setUserAccount] = useState([]);
  const getpassword = (e) => {
    setPassword(e.target.value);
  };
  const CreateAccount = async (e) => {
    e.preventDefault();
    // initWeb3();
    // console.log(web3);
    // let accounts = web3.eth.getAccounts();
    let accounts = await web3.eth.personal.newAccount(password);
    console.log("accounts : ", accounts);
    // 지금까지 생성된 계정 리스트로 쭉 나옴
    // console.log(web3.eth.personal.getAccounts());
    setUserAccount(accounts);
    console.log("userAccount : ", userAccount);

    // let balance = web3.eth.getBalance(accounts[0]);
    // console.log(balance);
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
