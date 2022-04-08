import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Styles from "./Hamburger.module.css";
import { actionCreators } from "../../store";
import { connect } from "react-redux";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";

function Hamburger({ state, DispatchdeleteInfo, setRendering }) {
    const token = sessionStorage.getItem("token")
    const role = sessionStorage.getItem("role");

    const logoutSuccess = () => {
        Swal.fire({
          title: "로그아웃!",
          text: "오늘도 좋은 하루 보내세요",
          icon: "success",
          confirmButtonColor: "#73E0C1",
          confirmButtonText: "확인",
        })
    };

    const logoutFail = () => {
        Swal.fire({
          title: "로그아웃 실패",
          icon: "error",
          confirmButtonColor: "#73E0C1",
          confirmButtonText: "확인",
        })
    };


    const navigation = useNavigate();

    //로그아웃
    const logout = () => {
        axios
        .get(
            "https://j6a304.p.ssafy.io/api/auth/logout",
            {
                headers: {
                    refreshToken: token,
                },
            }
        )
        .then((res) => {
            // console.log("res", res);
            sessionStorage.clear();
            DispatchdeleteInfo();
            logoutSuccess();
            setRendering(+1);
            navigation("/");
            
        })
        .catch(error => {
            // console.log("error",error);
            logoutFail();
            // console.log("로그아웃 실패");
        })
    }


    return(
        <>
            <label>
                <input type='checkbox' />
                    <span className={Styles.menu}>
                        <span className={Styles.hamburger}></span>
                    </span>
                    <ul>
                        <li></li>
                        <li>
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
                            <Link to='/polllist' className={Styles.poll}>Poll</Link>
                        </li>
                        <li>
                            <Link to='/hall' className={Styles.hall}>Hall</Link>
                        </li>
                        <li>
                            <Link to='/history' className={Styles.history}>History</Link>
                        </li>
                    </ul>
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