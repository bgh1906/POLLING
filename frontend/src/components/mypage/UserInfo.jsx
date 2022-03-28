import NewNav from "../layout/NewNav";



function UserInfo() {

    return (
        <>
          {/* <h>Item One</h> */}
          <input type={'text'} placeholder="Nickname"></input>
          <button>수정</button>
          <div>e-mail</div>
          <input type={'password'} placeholder="password"></input>
          <button>수정</button>
        </>
    );
}

export default UserInfo;