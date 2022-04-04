import NewNav from "../components/layout/NewNav";
import Styles from "./Notice.module.css";
//tabs
import Typography from '@mui/material/Typography';
import PropTypes from "prop-types";
import SwipeableViews from "react-swipeable-views";
import { useTheme } from "@mui/material/styles";
import AppBar from "@mui/material/AppBar";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import { useState } from "react";
import NoticeP from "../components/notice/NoticeP";
import NoticeU from "../components/notice/NoticeU";
import NoticeH from "../components/notice/NoticeH";

function TabPanel(props) {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`full-width-tabpanel-${index}`}
        // aria-labelledby={`full-width-tab-${index}`}
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
    value: PropTypes.number.isRequired
  };
  
  function a11yProps(index) {
    return {
      id: `full-width-tab-${index}`,
      "aria-controls": `full-width-tabpanel-${index}`
    };
  } 

function Notice() {

    //tabs
    const theme = useTheme();
    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const handleChangeIndex = (index) => {
        setValue(index);
    };

    
    return (
        <>
            <div style={{height:"100vh"}}>
                <NewNav />
                <div className={Styles.faq}>FAQ</div>
                {/* <Box sx={{ bgcolor: "background.paper", width: 500 }}> */}
                <Box className={Styles.accordion}>
                    <AppBar position="static" style={{backgroundColor:'#ffffff00', boxShadow: 'none'}} className={Styles.appbar}>
                        <Tabs
                          value={value}
                          onChange={handleChange}
                          indicatorColor="secondary"
                          textColor="inherit"
                          variant="fullWidth"
                          aria-label="full width tabs example"
                          className={Styles.tab}
                        >
                        <Tab className={Styles.tab} style={{fontFamily:'RussoOne', fontSize:'1.5vw'}} label="Poll" {...a11yProps(0)} />
                        <Tab className={Styles.tab} style={{fontFamily:'RussoOne', fontSize:'1.5vw'}} label="Hall" {...a11yProps(1)} />
                        <Tab className={Styles.tab} style={{fontFamily:'RussoOne', fontSize:'1.5vw'}} label="User" {...a11yProps(2)} />
                        </Tabs>
                    </AppBar>
                    <SwipeableViews
                        axis={theme.direction === "rtl" ? "x-reverse" : "x"}
                        index={value}
                        onChangeIndex={handleChangeIndex}
                    >
                        <TabPanel value={value} index={0} dir={theme.direction}>
                            {/* <div className={Styles.accordion}> */}
                            <NoticeP />
                        </TabPanel>
                        <TabPanel value={value} index={1} dir={theme.direction}>
                            <NoticeH />
                        </TabPanel>
                        <TabPanel value={value} index={2} dir={theme.direction}>
                            <NoticeU />
                        </TabPanel>
                    </SwipeableViews>
                </Box>
            </div>
        </>
    );
}

export default Notice;