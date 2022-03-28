import styles from "./Admin.module.css";
import { Link, useNavigate } from "react-router-dom";
import NewNav from "../components/layout/NewNav.jsx"
import { useState, useEffect } from "react";
import Footer from "../components/layout/Footer";
import Grid from '@mui/material/Grid';
import Moment from 'react-moment';
import logo from "../assets/mark_slim.png"
import Button from '@mui/material/Button';
import axios from "axios";
import { useSelector } from 'react-redux'



function Admin() {

    useEffect(()=>{
        window.scrollTo(0,0);
    }, [])

    const navigate = useNavigate();
    const [polldata, setPolldata] = useState([]);
    const [polldata2, setPolldata2] = useState([]);
    const [polldata3, setPolldata3] = useState([]);
    const [polldata4, setPolldata4] = useState([]);
    const [rendernumber, setRendernumber] = useState(0);

    const token = useSelector((state)=>(state[0].token));


    useEffect(()=>{

            axios
            .get("http://j6a304.p.ssafy.io:8080/api/polls/unapproved/0/50")
            .then((res) => {
                setPolldata(res.data);
            })
            .catch(error => {
                console.log(error.response)
            });  
            axios
            .get("http://j6a304.p.ssafy.io:8080/api/polls/wait/0/50")
            .then((res) => {
                setPolldata2(res.data);
            })
            .catch(error => {
                console.log(error.response)
            });  
    
            axios
            .get("http://j6a304.p.ssafy.io:8080/api/polls/progress/0/50")
            .then((res) => {
                setPolldata3(res.data);
            })
            .catch(error => {
                console.log(error.response)
            });  

            axios
            .get("http://j6a304.p.ssafy.io:8080/api/polls/done/0/50")
            .then((res) => {
                setPolldata4(res.data);
            })
            .catch(error => {
                console.log(error.response)
            });  
        }, [rendernumber]);


    function changeStatuswait(e){
        const poll_id = e.target.name;
        console.log(token)

        axios.patch(
         `http://j6a304.p.ssafy.io:8080/api/polls/admin/${poll_id}/wait`,
         {},
        {
            headers: {
                "Authorization":token,
            },
        })
        .then(()=>{
            setRendernumber(rendernumber+1);
        })
    }

    function changeStatusprogress(e){
        const poll_id = e.target.name;        
        axios.patch(
         `http://j6a304.p.ssafy.io:8080/api/polls/admin/${poll_id}/progress`,
         {},
        {
            headers: {
                "Authorization":token,
            },
        })
        .then(()=>{
            setRendernumber(rendernumber+1);
        })
    }

    function changeStatusdone(e){
        const poll_id = e.target.name;        
        axios.patch(
         `http://j6a304.p.ssafy.io:8080/api/polls/admin/${poll_id}/done`,
         {},
        {
            headers: {
                "Authorization":token,
            },
        })
        .then(()=>{
            setRendernumber(rendernumber+1);
        })
    }
    
    function moveToUpdate(e){
        const poll_id = e.target.name;
        navigate(`/poll/update/${poll_id}`);
    }
      

    return (
        <div>
              <NewNav />
{/* Header */}
            <div id={styles.title_header}>
                <div className={styles.title}>
                    <div >
                    <div style={{ textAlign:"center"}}>POLL</div>
                    <div>MANAGEMENT </div>
                    </div>
                    
                </div> 

                <div className={styles.create} >
                    <Link to="/createpoll" className={styles.create}>
                        CREATE A POLL
                    </Link>
                </div>
                <Moment id={styles.moment}></Moment>
            </div>

        <div className={styles.container}>
            <div style={{width:"100%"}}>
                <div id={styles.status}>Poll Status</div>
                <img id={styles.logo} src={logo} alt="logo" />

{/* unapproved */}
                <div id={styles.status_title}>UNAPPROVED
                    <div id={styles.status_kor}> 미승인 </div>
                </div>
                <Grid id={styles.poll_container} container>
                {polldata.map((poll)=> (
                    <Grid key={poll.id} id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                            <div id={styles.list_pollname}> {poll.title} </div>
                            <div id={styles.list_datefont}> 시작: {poll.startDate} <br/>종료: {poll.endDate} </div>
                            <div><Button id={styles.status_button1} onClick={moveToUpdate} name={poll.id} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} onClick={changeStatuswait} name={poll.id} variant="contained">투표 승인하기</Button></div>
                    </Grid>))}
                </Grid>
                <div id={styles.status_under}></div>  
{/* stand by */}
                <div id={styles.status_title}>STAND BY
                    <div id={styles.status_kor}> 대기 </div>
                </div>
                <Grid id={styles.poll_container} container>
                {polldata2.map((poll)=> (
                    <Grid key={poll.id} id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                            <div id={styles.list_pollname}> {poll.title} </div>
                            <div id={styles.list_datefont}> 시작: {poll.startDate} <br/>종료: {poll.endDate} </div>
                            <div><Button id={styles.status_button1} onClick={moveToUpdate} name={poll.id} variant="contained">투표 수정하기</Button></div>
                            <div><Button id={styles.status_button2} onClick={changeStatusprogress} name={poll.id} variant="contained">투표 시작하기</Button></div>
                    </Grid>))}
                </Grid>
                <div id={styles.status_under}></div>
{/* In progress */}       
                <div id={styles.status_title}>IN PROGRESS
                    <div id={styles.status_kor}> 진행중 </div>
                </div>
                <Grid id={styles.poll_container} container>
                {polldata3.map((poll)=> (
                    <Grid key={poll.id} id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                            <div id={styles.list_pollname}> {poll.title} </div>
                            <div id={styles.list_datefont}> 시작: {poll.startDate} <br/>종료: {poll.endDate} </div>
                            <div><Button id={styles.status_button1} onClick={changeStatusprogress} name={poll.id} variant="contained">투표 옵션변경</Button></div>
                            <div><Button id={styles.status_button2} onClick={changeStatusdone} name={poll.id} variant="contained">투표 종료하기</Button></div>
                    </Grid>))}
                </Grid>
                <div id={styles.status_under}></div>     
{/* history */}       
                <div id={styles.status_title}> CLOSED
                    <div id={styles.status_kor}> 종료 </div>
                </div>
                <Grid id={styles.poll_container} container>
                {polldata4.map((poll)=> (
                    <Grid key={poll.id} id={styles.pollbox} item xs={12} sm={6} lg={3}>
                            <img id={styles.list_img} src={poll.thumbnail} alt="main" />
                            <div id={styles.list_pollname}> {poll.title} </div>
                            <div id={styles.list_datefont}> 시작: {poll.startDate} <br/>종료: {poll.endDate} </div>
                            <div><Button id={styles.status_button1} variant="contained">NFT 발급</Button></div>
                            <div><Button id={styles.status_button2} variant="contained">HISTORY 추가</Button></div>
                    </Grid>))}
                </Grid>
                <div id={styles.status_under}></div>     
        </div>
</div>

        <Footer></Footer>
        </div>
    );
}

export default Admin;