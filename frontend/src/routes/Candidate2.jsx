import NewNav from "../components/layout/NewNav.jsx"
import styles from "./Candidate.module.css";
import Button from '@mui/material/Button';
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Comment from "../components/comment/Comment"
import mark from "../assets/mark_slim.png"
import crown from "../assets/crown.png"
import tx from "../assets/tx.png"
import axios from "axios";


function Candidate2() {

    const navigate = useNavigate();
    const params = useParams();

    const [candi_name, setCandi_name] = useState("")
    const [profile, setProfile] = useState("")
    const [profile_image, setProfile_image] = useState("" )
    const [photo1, setPhoto1] = useState("")
    const [photo2, setPhoto2] = useState("")
    const [photo3, setPhoto3] = useState("")
    const [voteCount, setVoteCount] = useState(0)
    const [commentdata, setCommentdata] = useState([])
    const [renderCount, setRenderCount] = useState(0)


    useEffect(()=>{
        axios.get(`https://j6a304.p.ssafy.io/api/polls/candidates/${params.id}`)
        .then((res) => {
            console.log(res)
            setProfile_image(res.data.thumbnail)
            setPhoto1(res.data.imagePath1)
            setPhoto2(res.data.imagePath2)
            setPhoto3(res.data.imagePath3)
            setCandi_name(res.data.name)
            setProfile(res.data.profile)
            setVoteCount(res.data.voteTotalCount)
            setCommentdata(res.data.comments)

        })
        .catch(error => {
            console.log(error.response)
        });  
    }, [renderCount]);

    function changePhoto1(){
        setProfile_image(photo1)
        setPhoto1(profile_image)
    }

    function changePhoto2(){
        setProfile_image(photo2)
        setPhoto2(profile_image)
    }
    
    function changePhoto3(){
        setProfile_image(photo3)
        setPhoto3(profile_image)
    }

    function gotoList(){
        navigate(`/poll/${params.pollnum}`)
    }

    function renderCheck(){
        setRenderCount((renderCount)=>(renderCount+1))
    }


    return (

        <div>
                <NewNav />
            <div className={styles.container}>
                <img id={styles.crown2} src={crown} alt="crown" />
                <img id={styles.tx2} src={tx} alt="tx2" />
                <span id={styles.name2}>{candi_name}</span>
                <p id={styles.profile2}>
                  {profile}
                </p>
                <img id={styles.profile_image2} src={profile_image} alt="profile_image" />
                <p id={styles.nowrank2}> 현재 순위: 1위 </p>
                <p id={styles.nowpoll2}> <img id={styles.mark} src={mark} alt={mark}/>현재 투표수: {voteCount}표 </p>
                <Button id={styles.poll_button2} variant="contained">투표하기</Button>
                <Button id={styles.con_button2} variant="contained">투표내역</Button>
                <Button id={styles.back_button2} onClick={gotoList} variant="contained">참가자 목록</Button>
                <div id={styles.photobox2}>
                    <img id={styles.photo1} 
                    onClick={changePhoto1}
                    src={photo1} alt="photo1" />
                    <img id={styles.photo2} 
                    onClick={changePhoto2}
                    src={photo2} alt="photo2" />
                    <img id={styles.photo3} 
                    onClick={changePhoto3}
                    src={photo3} alt="photo3" />
                </div>

                <Comment candiId={params.id} data={commentdata} renderCheck={renderCheck}></Comment>
                <Button id={styles.list_button} onClick={gotoList} variant="contained">리스트로 돌아가기</Button>


            </div>

        </div>
    );
}

export default Candidate2;