import NewNav from "../layout/NewNav";
import Styles from "./UserInfo.module.css"


function UserInfo() {

    return (
        <>
          {/* <h>Item One</h> */}
          <span className={Styles.textNickname}>Nickname : </span>
          <input type={'text'} placeholder="Nickname" className={Styles.nickname}></input>
          <button className={Styles.nicknamebtn}>수정</button>
          <div className={Styles.textEmail}>e-mail : </div>
          <input type={'email'} placeholder="E-mail" className={Styles.email} value="{}" readOnly></input>
          <span className={Styles.textPassword}>Password : </span>
          <input type={'password'} placeholder="password" className={Styles.password}></input>
          <button className={Styles.passwordbtn}>수정</button>
          <br />
          <button className={Styles.logout}>로그아웃</button>
          <button className={Styles.out}>탈퇴</button>

        </>
    );
}

export default UserInfo;