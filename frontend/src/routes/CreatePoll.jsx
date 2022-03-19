import styles from "./CreatePoll.module.css";
import Nav from "../components/layout/Nav.jsx"
import React, { useRef, useState } from "react";
import NomineeInput from "../components/admin/NomineeInput"
import NomineeList from "../components/admin/NomineeList"
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators } from "../store"
import Footer from "../components/layout/Footer";


function CreatePoll() {
    const [pollImage, setpollImage] = useState("");
    const [pollName, setpollName] = useState("");
    const [pollPeriod, setpollPeriod] = useState("");
    const [pollDescribe, setpollDescribe] = useState("");    
    const [pollRealtime, setpollRealtime] = useState(false);
    const [pollLatestTX, setpollLatestTX] = useState(false);
    const [pollAllTX, setpollAllTX] = useState(false);

    const dispatch = useDispatch();
    const state = useSelector((state) => state);
    const navigate = useNavigate();
    const no = useRef(1)
    const [nomiList, setnomiList] = useState([{
        id: 0,
        name: '',
        profile: '',
        profile_image: '',
        additional_image1: '',
        additional_image2: '',
        additional_image3: '',
    }])

    const onDel=(id)=>{
        setnomiList(nomiList.filter(nomiList => nomiList.id !== id))
    }
    const onAdd=(form)=>{
        form.id = no.current++;
        setnomiList(nomiList.concat(form));
    }

    

    function changeUrl(e) {
        setpollImage(e.target.value);
    }
    function changePollName(e) {
        setpollName(e.target.value);
    }
    function changePollPeriod(e) {
        setpollPeriod(e.target.value);
    }
    function changepollDescribe(e) {
        setpollDescribe(e.target.value);
    }
    function changepollRealtime(e) {
        if (e.target.checked === true){
            setpollRealtime(true);
        } else {
            setpollRealtime(false);
        }
    }
    function changepollLatestTX(e) {
        if (e.target.checked === true){
            setpollLatestTX(true);
        } else {
            setpollLatestTX(false);
        }
    }
    function changepollAllTX(e) {
        if (e.target.checked === true){
            setpollAllTX(true);
        } else {
            setpollAllTX(false);
        }
    }
    function savePolldate(){
        const pollInfo = {
            pollName: {pollName},
            pollPeriod: {pollPeriod},
            pollDescribe: {pollDescribe},
            pollRealtime: {pollRealtime},
            pollLatestTX: {pollLatestTX},
            pollAllTX: {pollAllTX},
            nomiList: {nomiList},
            status: "unapproved"
        }
        console.log(pollInfo)
        dispatch(actionCreators.addInfo(pollInfo));
    }

    



    return (
        <div >
            <div>
              <Nav />
            </div>
            <div style={{ margin: "0 10vw"}} >
                <h1 className={styles.title}> CREATE A POLL </h1>
            </div>
            <div className={styles.container} style={{marginBottom: "10vw"}}>
                <div id={styles.subtitle}> MAIN IMAGE </div>
                <div  id={styles.main_div}>
                    <span>이미지(URL)주소: </span> 
                    <input id={styles.poll_input} type="text" placeholder="이미지 주소를 입력하세요."
                    onChange={changeUrl}
                    />
                </div>
                    {/* <button id={styles.image_button} onClick={register_image}> 등록 </button> */}
                <div style={{ display:"flex", justifyContent:"center"}}>
                    {pollImage !== "" && (
                        <img src={pollImage} alt="pollimage" id={styles.poll_image} />
                        )}
                </div>

                <div id={styles.subtitle}> POLL TITLE </div>   
                <div  id={styles.main_div}>
                    <span>투표(대회) 이름:</span>
                    <input id={styles.poll_input} type="text" placeholder="투표 이름을 입력하세요. EX) 프로듀스 101"
                    onChange={changePollName}
                    />
                </div> 

                <div id={styles.subtitle}> POLLING PERIOD </div>   
                <div  id={styles.main_div}>
                    <span>투표(대회) 기간:</span>
                    <input id={styles.poll_input} type="text" placeholder="투표 기간을 입력하세요. EX) 2022.03.14 - 2022.04.17"
                    onChange={changePollPeriod}
                    />
                </div> 

                <div id={styles.subtitle}> DESCRIPTION </div>   
                <div  id={styles.main_div}>
                    <div>투표(대회) 설명:</div>
                    <textarea id={styles.poll_input2} type="text" placeholder="투표에 대한 설명을 입력하세요. 
                    EX) 마침내 세계가 놀랄 글로벌 아이돌을 탄생시킬 X의 베일이 벗겨진다. 
                    글로벌 아이돌 육성 프로젝트! 프로듀스 X 101"
                    onChange={changepollDescribe}
                    />
                </div> 

                <div id={styles.subtitle}> OPTION </div>   
                <div  id={styles.main_div}>
                    <div>투표(대회) 옵션:</div> 
                    <div id={styles.check_div}>
                        <div>
                            <input id={styles.poll_input3} type="checkbox" value="now"
                            onChange={changepollRealtime}/> 
                            <span id={styles.check_text}>실시간 투표 수 공개</span>
                        </div>
                        <div>
                            <input id={styles.poll_input3} type="checkbox" value="recent"
                            onChange={changepollLatestTX}/> 
                            <span id={styles.check_text}>투표 내역 공개 (최근 50 건)</span>
                        </div>
                        <div>
                            <input id={styles.poll_input3} type="checkbox" value="all"
                            onChange={changepollAllTX}/> 
                            <span id={styles.check_text}>전체 투표 내역 공개</span>
                        </div>
                    </div>
                </div> 
                <div id={styles.subtitle}> NOMINEE </div> 

                <NomineeInput onAdd={onAdd}/>
                <NomineeList nomiList={nomiList} onDel={onDel}/>
                
                <div id={styles.poll_savebox}>
                    <button id={styles.poll_save} onClick={()=>{
                        if (pollImage !=='' || pollName !=='' || pollPeriod !==''){
                            savePolldate();
                            navigate("/admin");
                        } else {
                            Swal.fire({
                                title: '투표 정보를 입력해주세요.',
                                icon: 'error'                        
                            })
                        }
                    }}>투표 생성하기</button>
                    <Link to="/admin" id={styles.poll_back}> <span>돌아가기</span></Link>

                </div>
                        
            </div>

        <Footer></Footer>
        </div>
    );
}

export default CreatePoll;