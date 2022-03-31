import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Styles from "./Hamburger.module.css";
import { actionCreators } from "../../store";
import { connect } from "react-redux";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";

function Hamburger({ state, DispatchdeleteInfo, setRendering }) {
// function Hamburger() {

    // const [sstoken, setToken] = useState(false);
    // useEffect(() => {
    //     const token = sessionStorage.getItem("token")
    //     if(token !== ""){
        //         setToken(!sstoken);
        //     }
        // },[])
    
    const token = sessionStorage.getItem("token")
    const role = sessionStorage.getItem("role");

    const logoutSuccess = () => {
        Swal.fire({
          title: "로그아웃!",
        //   text: "POLLING을 이용해주셔서 감사합니다.",
          text: "오늘도 좋은 하루 보내세요",
          icon: "success",
          confirmButtonColor: "#73E0C1",
          confirmButtonText: "확인",
        })
    };

    const logoutFail = () => {
        Swal.fire({
          title: "로그아웃 실패",
        //   text: "POLLING을 이용해주셔서 감사합니다.",
          icon: "error",
          confirmButtonColor: "#73E0C1",
          confirmButtonText: "확인",
        })
    };

    // const token2 = state[0].token;

    const navigation = useNavigate();

    //로그아웃
    const logout = () => {
        axios
        .get(
            // "https://j6a304.p.ssafy.io:8080/api/auth/logout",
            "https://j6a304.p.ssafy.io/api/auth/logout",
            // {},
            {
                headers: {
                    // "Authorization":token,
                    refreshToken: token,
                },
            })
        .then((res) => {
            console.log("res", res);
            console.log("로그아웃");
            sessionStorage.clear();
            DispatchdeleteInfo();
            logoutSuccess();
            setRendering(+1);
            navigation("/");
            
        })
        .catch(error => {
            console.log("error",error);
            logoutFail();
            console.log("로그아웃 실패");
        })
    }


    return(
        <>
            <label>
                <input type='checkbox' />
                    {/* <span class='menu' className={Styles.menu}> */}
                    <span className={Styles.menu}>
                        {/* <span class='hamburger' className={Styles.hamburger}></span> */}
                        <span className={Styles.hamburger}></span>
                    </span>
                    <ul>
                    {/* <div> */}
                        <li></li>
                        <li>
                            {/* <a href='/'>Home</a> */}
                            <Link to='/' className={Styles.home}>Home</Link>
                        </li>
                        <li>
                            { 
                            token === null ?
                            <Link to='/login' className={Styles.home}>login</Link>
                            :
                            <div className={Styles.logout} onClick={logout}>logout</div>    
                            }
                        </li>
                        <li>
                            {/* <a href='/polllist'>Poll</a> */}
                            <Link to='/polllist' className={Styles.poll}>Poll</Link>
                        </li>
                        <li>
                            {/* <a href='/hall'>Hall</a> */}
                            <Link to='/hall' className={Styles.hall}>Hall</Link>
                        </li>
                        <li>
                            {/* <a href='/history'>History</a> */}
                            <Link to='/history' className={Styles.history}>History</Link>
                        </li>
                    </ul>
                    {/* </div> */}
            </label>
        </>
    )
}

function mapStateToProps(state) {
    return { state };
  }
  function mapDispatchToProps(dispatch) {
    return {
      DispatchdeleteInfo: () => dispatch(actionCreators.deleteInfo()),
    };
  }

export default connect(mapStateToProps, mapDispatchToProps)(Hamburger);
// export default Hamburger;