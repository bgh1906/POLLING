import NewNav from "../components/layout/NewNav";
import Styles from "./Mypage.module.css";

import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import { useState } from "react";
import UserInfo from "../components/mypage/UserInfo";
import Mypoll from "../components/mypage/Mypoll";
import Qnawrite from "../components/mypage/Qnawrite";
import QnaList2 from "../components/mypage/QnaList2";

function TabPanel(props) {

    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`simple-tabpanel-${index}`}
        aria-labelledby={`simple-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box sx={{ p: 3 }}>
            <div>{children}</div>
          </Box>
        )}
      </div>
    );
  }
  
  TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.number.isRequired,
    value: PropTypes.number.isRequired,
  };
  
  function a11yProps(index) {
    return {
      id: `simple-tab-${index}`,
      'aria-controls': `simple-tabpanel-${index}`,
    };
  }

function Mypage() {

     //1:1문의 창 띄우기
     const [qnaopen, setQnaopen] = useState(false);
     const getQnaopen = () => {
         setQnaopen(!qnaopen);
         if(listopen === false){
             setListopen(true);
         }
     }
 
     //1:1문의 목록 띄우기
     const [listopen, setListopen] = useState(true);
     const getListopen = () => {
         setListopen(!listopen);
         if(qnaopen === false){
             setQnaopen(true);
         }
     }

    const [value, setValue] = useState(0);

    //탭에 따른 내용 변경
    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const [newnick, setNewnick] = useState("");

    return (
        <div>
            <NewNav newnick={newnick}/>

            <div className={Styles.mypage}> My page </div>

            <Box sx={{ width: '80vw', paddingTop: '29vh', paddingLeft: '17vw'}}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                        <Tab label="정보 수정" {...a11yProps(0)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}} className={Styles.tabs}/>
                        <Tab label="투표 내역" {...a11yProps(1)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}} className={Styles.tabs}/>
                        <Tab label="1:1 문의" {...a11yProps(2)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}} className={Styles.tabs}/>
                    </Tabs>
                </Box>
                <TabPanel value={value} index={0}>
                    <UserInfo setNewnick={setNewnick}/>
                </TabPanel>
                <TabPanel value={value} index={1}>
                    <Mypoll />
                </TabPanel>
                <TabPanel value={value} index={2}>
                    <button onClick={getQnaopen} disabled={qnaopen === true? false:true} className={Styles.qna}>write</button>
                    <button onClick={getListopen} disabled={listopen === true? false:true}  className={Styles.list}>list</button>
                    <div hidden={qnaopen}>
                        <Qnawrite />
                    </div>
                    <div hidden={listopen}>
                          <QnaList2 />
                    </div>
                </TabPanel>
            </Box>
        </div>
    );
}

export default Mypage;