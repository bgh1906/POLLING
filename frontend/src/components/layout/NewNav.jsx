import { Link } from "react-router-dom";
import Styles from "./Newnav.module.css";
import mark_slim from "../../assets/mark_slim.png";
import Hamburger from "./Hamburger";
import { useEffect, useState } from "react";

function NewNav() {

    const role = sessionStorage.getItem("role")
    const [rendering, setRendering] = useState(0)
    //햄버그에서 로그아웃할때 네브도 리랜더해야함... 어떻게??
    

    return (
        <div >
            <div className={Styles.outbox}></div>
            <div>
                <Link to="/"><img src={mark_slim}  alt="mark" className={Styles.mark} /></Link>
                <Link to="/" className={Styles.title}>POLLING</Link>
            </div>

                {/* <Link to="/login" className={Styles.hamNav1}></Link> */}
            <div>
                <Hamburger setRendering={setRendering} />
                {/* <button className={Styles.hamNav1}></button>
                <button className={Styles.hamNav2}></button>
                <button className={Styles.hamNav3}></button> */}
            </div>
            <div className={Styles.user}>
                <div className={Styles.userNav}>
                    <Link to="/" className={Styles.text}> {" "}Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                    {
                        // role === "" ?
                        role === null ?
                        <Link to="/login" className={Styles.text}> {" "}Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
                        : (
                            role === "ROLE_USER"?
                            <Link to="/mypage" className={Styles.text}> Mypage&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                            : (
                                role === "ROLE_ADMIN" ?
                                <Link to="/management" className={Styles.text}> admin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                                : 
                                ( role === "ROLE_COMPANY"?
                                <Link to="/management" className={Styles.text}> Company&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Link>
                                :
                                <Link to="/login" className={Styles.text}> {" "}Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{" "} </Link>
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
                    <Link to="/history" className={Styles.text} style={{textDecoration: 'none'}}> {" "}History{" "} </Link>
                </div>
            </div>
    </div>
  );
}

export default NewNav;
