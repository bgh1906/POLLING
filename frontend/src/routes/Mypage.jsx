import NewNav from "../components/layout/NewNav";
import Styles from "./Mypage.module.css";

import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useState } from "react";
import UserInfo from "../components/mypage/UserInfo";
import Mypoll from "../components/mypage/Mypoll";
import Qna from "../components/mypage/Qna";
import QnaList from "../components/mypage/QnaList";

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
            <Typography>{children}</Typography>
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

    const [value, setValue] = useState(0);

    //탭에 따른 내용 변경
    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <div>
            <NewNav />

            <div className={Styles.mypage}> My page </div>

            <Box sx={{ width: '80vw', paddingTop: '35vh', paddingLeft: '17vw'}}>
            {/* <Box className={Styles.firstbox}> */}
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                    <Tab label="정보 수정" {...a11yProps(0)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}} className={Styles.tabs}/>
                    <Tab label="투표 내역" {...a11yProps(1)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}}v className={Styles.tabs}/>
                    <Tab label="1:1 문의" {...a11yProps(2)} style={{fontFamily:"GangwonEdu_OTFBoldA", fontSize:'1.4vw'}} className={Styles.tabs}/>
                    </Tabs>
                </Box>
                <TabPanel value={value} index={0}>
                    <UserInfo />
                </TabPanel>
                <TabPanel value={value} index={1}>
                    <Mypoll />
                </TabPanel>
                <TabPanel value={value} index={2}>
                    <QnaList />
                </TabPanel>
            </Box>
        </div>
    );
}

export default Mypage;