import NewNav from "../components/layout/NewNav.jsx"
import styles from "./Candidate.module.css";
import Button from '@mui/material/Button';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Comment from "../components/comment/Comment"


function Candidate() {

    const navigate = useNavigate();

    const [profile_image, setProfile_image] = useState("https://blog.kakaocdn.net/dn/DPDsG/btqJ1LYvkyG/6qifiqBgq9pkh7n1YbeAaK/img.png" )
    const [photo1, setPhoto1] = useState("http://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/10/18/a706569b-e8db-4c05-bbd1-2c48a53a0f2f.jpg")
    const [photo2, setPhoto2] = useState("https://photo.jtbc.joins.com/news/jam_photo/202110/20/acdbb4bc-d5b8-4a0f-88b1-376efbf966a3.jpg")
    const [photo3, setPhoto3] = useState("https://onimg.nate.com/orgImg/is/2017/12/15/htm_20171215103039558796.jpg")


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
        navigate("/poll/:pollnum")
    }


    return (

        <div>
                <NewNav />
            <div className={styles.container}>
                <span id={styles.name}>수지</span>
                <p id={styles.profile}>
                    출생: 1994. 10. 10. <br />
                    소속사: 매니지먼트 숲 <br />
                    데뷔: 2010년 미쓰에이 싱글 앨범 [Bad But Good] <br />
                    수상: 2021년 제16회  <br />서울드라마어워즈 연기자상
                </p>
                <img id={styles.profile_image} src={profile_image} alt="profile_image" />
                <p id={styles.nowrank}> 현재 순위: 1위 </p>
                <p id={styles.nowpoll}> 현재 투표수: 7548표 </p>
                <Button id={styles.poll_button} variant="contained">투표하기</Button>
                <Button id={styles.con_button} variant="contained">투표내역</Button>
                <Button id={styles.back_button} onClick={gotoList} variant="contained">참가자 목록</Button>
                <div id={styles.photobox}>
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

                <Comment></Comment>
                <Button id={styles.list_button} onClick={gotoList} variant="contained">리스트로 돌아가기</Button>
            </div>

        </div>
    );
}

export default Candidate;