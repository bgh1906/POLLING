//Accordion
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from 'react';
import Styles from "../../routes/Notice.module.css";

function NoticeH () {
    //accordian
    const [expanded, setExpanded] = useState(false);

    const handleChangeA = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };


    return(

        <div>
            <Accordion expanded={expanded === 'panel1'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel1')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1bh-content"
                    id="panel1bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        명예의 전당이란?
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            명예의 전당은 역대 대회들의 우승자들을 전시한 공간입니다. <br />
                            이 페이지를 통해 이전에 진행된 대회들과, 각각의 우승자들을 한눈에 볼 수 있습니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel2'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel2')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel2bh-content"
                    id="panel2bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        명예의 전당 등록 기준
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            명예의 전당은 대회 우승자들을 위한 공간으로 각 대회의 우승자들에 한해서 등록이 가능합니다.
                    </div>
                </AccordionDetails>
            </Accordion>
            <Accordion expanded={expanded === 'panel3'} style={{backgroundColor:"#3afaca15"}} onChange={handleChangeA('panel3')}>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel3bh-content"
                    id="panel3bh-header"
                >
                    <div style={{fontFamily:"ROKAFSansMedium", fontWeight:'bold', fontSize:'0.9vw'}} sx={{ width: '33%', flexShrink: 0 }} className={Styles.aaccordiontitle}>
                        명예의 전당 혜택 
                    </div>
                </AccordionSummary>
                <AccordionDetails>
                    <div style={{fontFamily:"GangwonEdu_OTFBoldA", fontWeight:'bold'}} className={Styles.aaccordioncontent}>
                            명예의 전당에 오른 우승자들에게는 개인이 소유할 수 있는 NFT를 발급해드릴 예정입니다. <br />
                            자신만의 고유 NFT를 노려보세요!!
                    </div>
                </AccordionDetails>
            </Accordion>
        </div>
    )
}

export default NoticeH;