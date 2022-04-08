import { Link } from "react-router-dom";
import Styles from "./Newnav.module.css";
import mark_slim from "../../assets/mark_slim.png";
import Hamburger from "./Hamburger";
import { useEffect, useState } from "react";
import { connect } from "react-redux";
import { actionCreators } from "../../store";
import { Avatar } from "@mui/material";
import token2 from "../../assets/token.png"
import {checkPOL} from "../../contracts/CallContract";

function NewNav({state, reward, tminus, newnick}) {
    const wallet = sessionStorage.getItem("wallet");
    const role = sessionStorage.getItem("role");
    const nickname = sessionStorage.getItem("nickname");
    let firstnick ="";
    if(nickname !== null){
        firstnick = nickname.substring(0, 1);
    }

    //토큰값 받아오는 함수
    const [token1, setToken1] = useState();
    
    const getToken1 = () => {
        //없을때 아웃
        if(wallet === null){
            return;
        }
        //checkPOL
        checkPOL(wallet).then(res => setToken1(res));
    };

    useEffect(() => {
        getToken1();
    })


    //햄버거 버튼에서 로그아웃하면, 같이 바뀌게?
    const [rendering, setRendering] = useState(0);

    return (
        <div >
            <div className={Styles.outbox}></div>
            <div>
                <Link to="/"><img src={mark_slim}  alt="mark" className={Styles.mark} /></Link>
                <Link to="/" className={Styles.title}>POLLING</Link>
            </div>
            <div>
                {nickname === null?
                    <div className={Styles.mininick}>
                    </div>
                    :
                    (
                        <div>
                            <div className={Styles.mininick}>
                                <Avatar  style={{fontSize:'1.5vw',backgroundColor:'#77a3a9', width:'2vw', height:'4vh', top:'-0.1vh'}}>{""}{firstnick}</Avatar>&nbsp;{nickname}
                            </div>
                            <div>
                                <img className={Styles.tokenimg} src={token2}/>
                                <div className={Styles.token}>{token1}</div>
                            </div>
                        </div>
                    )
                }
            </div>
            <div>
                <Hamburger setRendering={setRendering} />
            </div>
            <div className={Styles.user}>
                <div className={Styles.userNav}>
                    <Link to="/whypolling" className={Styles.text}> {" "}ABOUT&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                    {
                        role === null ?
                        <Link to="/login" className={Styles.text}> {" "}LOGIN&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                        : (
                            role === "ROLE_USER"?
                            <Link to="/mypage" className={Styles.text}> MYPAGE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                            : (
                                role === "ROLE_ADMIN" ?
                                <Link to="/management" className={Styles.text}> ADMIN&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                                : 
                                ( role === "ROLE_COMPANY"?
                                <Link to="/company" className={Styles.text}> COMPANY&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                                :
                                <Link to="/login" className={Styles.text}> {" "}LOGIN&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                                )
                            )
                        )
                    }
                    <Link to="/notice" className={Styles.text}> {" "}FAQ{" "} </Link>
                </div>
            </div>
            <div className={Styles.poll}>
                <div className={Styles.pollNav}>
                    <Link to="/polllist" className={Styles.text} style={{textDecoration: 'none'}}> {" "} POLL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                    <Link to="/hall" className={Styles.text} style={{textDecoration: 'none'}}> {" "}HALL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                    <Link to="/history" className={Styles.text} style={{textDecoration: 'none'}}> {" "}HISTORY{" "} </Link>
                </div>
            </div>
    </div>
  );
}
function mapStateToProps(state) {
    return { state };
}

// export default NewNav;
export default connect(mapStateToProps)(NewNav);
