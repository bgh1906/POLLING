import styles from "./CreatePoll.module.css";
import NewNav  from "../components/layout/NewNav.jsx"
import React, { useRef, useState } from "react";
import NomineeInput from "../components/admin/NomineeInput"
import NomineeList from "../components/admin/NomineeList"
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators } from "../store"
import Footer from "../components/layout/Footer";
import logo from "../assets/mark_slim.png"
import nonono from "../assets/nonono.png"
import TextField from '@mui/material/TextField';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DateTimePicker from '@mui/lab/DateTimePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import dayjs from "dayjs";






function CreatePoll() {
    const [pollImage, setpollImage] = useState("");
    const [pollName, setpollName] = useState("");
    const [pollStart, setpollStart] = useState("");
    const [pollEnd, setpollEnd] = useState("");
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
    function changePollStart(e) {
        const startdate = dayjs(e).format("YYYY-MM-DD HH:mm")
        setpollStart(startdate);
        console.log(startdate)
    }
    function changePollEnd(e) {
        const enddate = dayjs(e).format("YYYY-MM-DD HH:mm")
        setpollEnd(enddate);
        console.log(enddate)
        
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



    function savePolldata(){
        const pollInfo = {
            pollName: {pollName},
            pollStart: {pollStart},
            pollEnd: {pollEnd},
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
              <NewNav  />
            </div>
            <div>
                <div className={styles.title}> CREATE A POLL </div>
            </div>
            
            <div className={styles.container} style={{marginBottom: "1vw"}}>
                <div id={styles.info}> POLL&nbsp;&nbsp;INFORMATION </div>
                <img id={styles.logo} src={logo} alt="logo" /> 
                <div id={styles.box1}></div>
                <div id={styles.box2}>
                    {pollImage === "" && (
                        <img src={nonono} alt="noimage" id={styles.no_image} />
                        )}
                    {pollImage !== "" && (
                        <img src={pollImage} alt="pollimage" id={styles.poll_image} />
                        )}
                    <div id={styles.poll_title}>
                        <span id={styles.input_name3}>Poll Title</span>
                        <TextField id={styles.title_input}
                        onChange={changePollName}
                        variant="standard" placeholder="투표 이름을 입력하세요."/>
                    </div>
                    <div id={styles.poll_title2}>
                        <span id={styles.input_name}>Main Image</span>
                        <TextField id={styles.title_input}
                        onChange={changeUrl}
                        variant="standard" placeholder="이미지 주소를 입력하세요."/>
                    </div>
                    <div id={styles.poll_title3}>
                        <span id={styles.input_name2}> Deadline </span>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DateTimePicker
                            id={styles.datepick}
                            label="투표 시작일"
                            value={pollStart}
                            onChange={changePollStart}
                            inputFormat={"yyyy-MM-dd HH:mm"}
                            mask={"____-__-__"}
                            renderInput={(params) => <TextField {...params} />}/>
                        &nbsp;&nbsp;&nbsp;
                        <DateTimePicker
                            id={styles.datepick}
                            label="투표 종료일"
                            value={pollEnd}
                            onChange={changePollEnd}
                            inputFormat={"yyyy-MM-dd HH:mm"}
                            mask={"____-__-__"}
                            renderInput={(params) => <TextField {...params} />}/>
                        </LocalizationProvider>
                    </div>
                    <div id={styles.poll_title4}>
                        <span id={styles.input_name4}> Poll Option </span>
                    
                        <div id={styles.check_div}>
                                <input id={styles.poll_input3} type="checkbox" value="now"
                                onChange={changepollRealtime}/> 
                                <span id={styles.check_text}>실시간 투표 수 공개</span>
                                <input id={styles.poll_input3} type="checkbox" value="recent" 
                                onChange={changepollLatestTX}/> 
                                <span id={styles.check_text}>투표 내역 공개 (최근 50건)</span>
                                <input id={styles.poll_input3} type="checkbox" value="all"
                                onChange={changepollAllTX}/> 
                                <span id={styles.check_text}>전체 투표 내역 공개</span>
                        </div>
                    </div>
                    <div id={styles.poll_title5}>
                        <span id={styles.input_name5}> Description </span>
                        <TextField
                            id={styles.poll_input2}
                            multiline
                            rows={4}
                            onChange={changepollDescribe}
                            placeholder="투표에 대한 설명을 입력하세요."
                            />
                    </div>
                    <div id={styles.input_name6}> Candidate Registration </div> 
                </div>

                <NomineeInput onAdd={onAdd}/>
                <NomineeList nomiList={nomiList} onDel={onDel}/>
                
                <div id={styles.poll_savebox}>
                    <button id={styles.poll_save} onClick={()=>{
                        if (pollImage !=='' || pollName !=='' || pollStart !==''){
                            savePolldata();
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