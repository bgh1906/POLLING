import styles from "./CreatePoll.module.css";
import NewNav  from "../components/layout/NewNav.jsx"
import React, { useRef, useState, useEffect } from "react";
import NomineeInput2 from "../components/admin/NomineeInput2"
import NomineeList2 from "../components/admin/NomineeList2"
import { Link, useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { useSelector } from 'react-redux'
import { actionCreators } from "../store"
import Footer from "../components/layout/Footer";
import logo from "../assets/mark_slim.png"
import nonono from "../assets/nonono.png"
import TextField from '@mui/material/TextField';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DateTimePicker from '@mui/lab/DateTimePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import dayjs from "dayjs";
import axios from "axios";






function UpdatePoll() {
    const [pollImage, setpollImage] = useState("");
    const [pollName, setpollName] = useState("");
    const [pollStart, setpollStart] = useState("");
    const [pollEnd, setpollEnd] = useState("");
    const [pollDescribe, setpollDescribe] = useState("");    
    const [pollRealtime, setpollRealtime] = useState(false);

    const params = useParams();

    // const token = useSelector((state)=>(state[0].token));
    const token = sessionStorage.getItem("token")

    const navigate = useNavigate();
    const no = useRef(1)
    const [nomiList, setnomiList] = useState([{
        id: 0,
        name: "",
        profile: "",
        thumbnail: "",
        imagePath1: "",
        imagePath2: "",
        imagePath3: ""
    }])

    

    useEffect(()=> {
        axios.get(`https://j6a304.p.ssafy.io:8080/api/polls/admin/${params.pollnum}`)
            .then((res) => {
                console.log("성공!");
                console.log(res);
                setpollName(res.data.title)
                setpollImage(res.data.thumbnail)
                setpollStart(res.data.startDate)
                setpollEnd(res.data.endDate)
                setpollDescribe(res.data.content)
                setnomiList(res.data.candidates)
                setpollRealtime(res.data.openStatus)
            })
            .catch(error => {
                console.log(error.response)
            });  
    },[])



    const [current, setCurrent] = useState({})
    const [isEdit, setIsEdit] = useState(false)


    
    const onEdit=(nominee)=>{
        setCurrent(nominee)    
        console.log(current)
        setIsEdit(true) 
        console.log(nomiList)
    }

    const onUpdate=(nominee)=>{
        setnomiList(nomiList.map(nomilist=> nomilist.candidateId===nominee.candidateId ? nominee : nomilist ))
        setIsEdit(false);        
    }


    const onDel=(id)=>{
        setnomiList(nomiList.filter(nomiList => nomiList.candidateId !== id))
    }
    const onAdd=(form)=>{
        form.id = no.current++;
        setnomiList((nomiList)=> nomiList.concat(form));
        addCandi(form)
        console.log("추가")
    }

    

    function changeUrl(e) {
        setpollImage(e.target.value);
    }
    function changePollName(e) {
        setpollName(e.target.value);
    }
    function changePollStart(e) {
        const startdate = dayjs(e).format("YYYY-MM-DD HH:mm")
        setpollStart(String(startdate));
        // console.log(String(startdate))
    }
    function changePollEnd(e) {
        const enddate = dayjs(e).format("YYYY-MM-DD HH:mm")
        setpollEnd(String(enddate));
        // console.log(enddate)
        
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
    

    function updatePolldata(){
        
        // console.log(pollInfo)
        
        axios.put(
            `https://j6a304.p.ssafy.io:8080/api/polls/admin/${params.pollnum}`,
            {
                "content":pollDescribe,
                "endDate":pollEnd,
                "openStatus":pollRealtime,
                "startDate":pollStart,
                "thumbnail":pollImage,
                "title":pollName
            },
            {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": token,
                    "Accept" : "*/*",
                },
            }
        )
        .then((res) =>{
            Swal.fire({
                title: '투표가 수정되었습니다.',
                icon: 'success'                        
            })
        })
        .then(()=>{
            navigate("/admin");
        })
        .catch(error => {
            console.log(error.response)
        });  

    }

    function deletePoll(){
        axios.delete(
            `https://j6a304.p.ssafy.io:8080/api/polls/admin/${params.pollnum}`,
            {
                headers: {
                    "Authorization": token,
                }
            }
        )
        .then(() =>{
            Swal.fire({
                title: '투표가 삭제되었습니다.',
                icon: 'success'                        
            })
        })
        .then(()=>{
            navigate("/admin");
        })
        .catch(error => {
            console.log(error.response)
        });
    }


    function patchCandi(nominee){
        axios.put(
            `https://j6a304.p.ssafy.io:8080/api/polls/admin/candidates/${nominee.candidateId}`,
            {
                "imagePath1": nominee.imagePath1,
                "imagePath2": nominee.imagePath2,
                "imagePath3": nominee.imagePath3,
                "name": nominee.name,
                "profile": nominee.profile,
                "thumbnail": nominee.thumbnail
              },
            {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": token,
                    "Accept" : "*/*",
                },
            }
        )
        .then((res) =>{
            console.log("후보자 정보 수정 성공!!")
        })
        .catch(error => {
            console.log(error.response)
        });  

    }

    function deleteCandi(id){
        axios.delete(
            `https://j6a304.p.ssafy.io:8080/api/polls/admin/candidates/${id}`,
            {
                headers: {
                    "Authorization": token,
                }
            }
        )
        .then(() =>{
            console.log("delete 성공!!")
            onDel(id)
        })
        .catch(error => {
            console.log(error.response)
        });
    }

    function addCandi(form){

        axios.post(
            `https://j6a304.p.ssafy.io:8080/api/polls/admin/candidate`,
            {
                "imagePath1": form.imagePath1,
                "imagePath2": form.imagePath2,
                "imagePath3": form.imagePath3,
                "name": form.name,
                "pollId":params.pollnum,
                "profile": form.profile,
                "thumbnail": form.thumbnail
              },
            {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": token,
                    "Accept" : "*/*",
                } 
            })
            .then(() =>{
                console.log("후보자 추가 성공!!")
            })
            .catch(error => {
                console.log(error.response)
            });
        
    }




    return (
        <div >
              <NewNav  />
            <div className={styles.title}> UPDATE THE POLL </div>
            
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
                        onChange={changePollName} value={pollName}
                        variant="standard" placeholder="투표 이름을 입력하세요."/>
                    </div>
                    <div id={styles.poll_title2}>
                        <span id={styles.input_name}>Main Image</span>
                        <TextField id={styles.title_input}
                        onChange={changeUrl} value={pollImage}
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
                            {pollRealtime? <input id={styles.poll_input3} checked type="checkbox" value={pollRealtime}
                                onChange={changepollRealtime}/> : <input id={styles.poll_input3} type="checkbox" value={pollRealtime}
                                onChange={changepollRealtime}/> }
                    
                                <span id={styles.check_text}>실시간 투표 수 공개</span>
                                
                        </div>
                    </div>
                    <div id={styles.poll_title5}>
                        <span id={styles.input_name5}> Description </span>
                        <TextField
                            id={styles.poll_input2}
                            multiline
                            rows={4}
                            onChange={changepollDescribe}
                            value={pollDescribe}
                            placeholder="투표에 대한 설명을 입력하세요."
                            />
                    </div>
                    <div id={styles.input_name6}> Candidate Registration </div> 
                </div>

                <NomineeInput2 onAdd={onAdd} current={current} isEdit={isEdit} onUpdate={onUpdate} patchCandi={patchCandi} addCandi={addCandi}/>
                <NomineeList2 nomiList={nomiList} onDel={onDel} onEdit={onEdit} deleteCandi={deleteCandi}/>
                
                <div id={styles.poll_savebox}>
                    <button id={styles.poll_save} onClick={()=>{
                        if (pollImage !=='' || pollName !=='' || pollStart !==''){
                            updatePolldata();
                            
                        } else {
                            Swal.fire({
                                title: '투표 정보를 입력해주세요.',
                                icon: 'error'                        
                            })
                        }
                    }}>수정하기</button>
                    <button id={styles.poll_delete} onClick={deletePoll}>삭제하기</button>
                    <Link to="/admin" id={styles.poll_back}> <span>돌아가기</span></Link>

                </div>
            </div>

        <Footer></Footer>
        </div>
    );
}

export default UpdatePoll;