// import Navbar from "@/components/Navbar";
// import LinearChart from "@/components/LinearChart";
// import {Fab} from "@mui/material";
// import Grid from "@mui/material/Grid";
// import MetricsCard from "@/components/MetricsCard";
// import {useState} from "react";
// import ChangeEvalPopup from "@/components/ChangeEvalPopup";
// import EditIcon from "@mui/icons-material/Edit";
// // import {getStudentEvaluation} from "@/services/SubClass/getStudentEvaluation";
//
// export default function Student({student}) {
//     const [showPopup, setShowPopup] = useState(false);
//     const mockData = [{subjectName:"Maths", entryIds: [5,1], attention: 5},{subjectName:"BEL", entryIds: [2,4], attention: 6}, {subjectName:"AE", entryIds: [3,6,7], attention: 4}]
//
//     return (
//         <main
//             className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10`}
//         >
//             <Navbar isStudent={false}/>
//             <ChangeEvalPopup isOpen={showPopup} onClose={()=>{setShowPopup(false)}} evaluations={mockData}/>
//             <Fab aria-label="edit" onClick={()=>{setShowPopup(true)}} className="absolute bottom-10 right-10">
//                 <EditIcon color="primary" />
//             </Fab>
//             <div className="flex flex-col space-y-5">
//                 <p className="flex justify-center text-3xl text-neutral-300 pb-5">{student.name}&apos;s attention</p>
//                 <div className="flex flex-row pb-10">
//                 <Grid container spacing={10}>
//                         <Grid item>
//                             <MetricsCard title="this month" attention={student.attentionMonth}/>
//                         </Grid>
//                         <Grid item>
//                             <MetricsCard title="this week" attention={student.attentionWeek}/>
//                         </Grid>
//                         <Grid item>
//                             <MetricsCard title="today" attention={student.attentionToday}/>
//                         </Grid>
//                     </Grid>
//                 </div>
//                 <div className="flex items-center space-x-20">
//                     <div className="w-full">
//                         <LinearChart
//                             title={"This year"}
//                             labels={student.attentionYearLabels}
//                             userData={student.attentionYearData}
//                             yAxisText="attention"
//                             xAxisText="time"
//                             datasetLabel="Students paying attention"
//                             stepSize={1}
//                         />
//                     </div>
//                 </div>
//             </div>
//         </main>
//     );
// }
//
// export async function getServerSideProps({params}) {
//     // const month = await getStudentEvaluation(params.id, 6);
//     // const week =  await getStudentEvaluation(params.id, 1);
//     // const day =  await getStudentEvaluation(params.id, 0);
//     return {
//         props: {
//             student: {
//                 name: "Nikola",
//                 id: 1,
//                 attentionMonth: 7,
//                 attentionWeek: 6,
//                 attentionToday: 7,
//                 attentionYearLabels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
//                 attentionYearData: [9, 4, 6, 7, 8, 5, 3, 4, 6, 7],
//             },
//         }
//     }
//     // return {
//     //     notFound: true,
//     // }
// }

import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import {CircularProgress, Fab} from "@mui/material";
import Grid from "@mui/material/Grid";
import MetricsCard from "@/components/MetricsCard";
import {useEffect, useState} from "react";
import ChangeEvalPopup from "@/components/ChangeEvalPopup";
import EditIcon from "@mui/icons-material/Edit";
import {getStudentEvaluation} from "@/services/SubClass/getStudentEvaluation";
import Box from "@mui/material/Box";
import {useRouter} from "next/router";

export default function Student() {
    const [student, setStudent] = useState(null);
    const [showPopup, setShowPopup] = useState(false);
    const mockData = [{subjectName:"Maths", entryIds: [5,1], attention: 5},{subjectName:"BEL", entryIds: [2,4], attention: 6}, {subjectName:"AE", entryIds: [3,6,7], attention: 4}];
    const router = useRouter();
    const { id, name } = router.query;
    // const id = parseInt(idTMP);

    useEffect(() => {
        const fetchData = async () => {
            try {
                console.log(id);
                const month = await getStudentEvaluation(id, 6);
                const week =  await getStudentEvaluation(id, 1);
                const day =  await getStudentEvaluation(id, 0);
                const tmp = {
                    name: name,
                    id: id,
                    attentionMonth: month,
                    attentionWeek: week,
                    attentionToday: day,
                    attentionYearLabels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
                    attentionYearData: [9, 4, 6, 7, 8, 5, 3, 4, 6, 7],
                }
                console.log(tmp);
                setStudent(tmp);
            } catch (error) {
                console.error('Error fetching student data:', error);
            }
        };

        fetchData();
    }, []);

    if(student === null) {
        return (
            <div className="flex justify-center items-center min-h-screen bg-neutral-900">
                <Box sx={{ display: 'flex', color: 'grey.500' }}>
                    <CircularProgress color="inherit" />
                </Box>
            </div>
        );
    }

    return (
        <main
            className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10`}
        >
            <Navbar isStudent={false}/>
            <ChangeEvalPopup isOpen={showPopup} onClose={()=>{setShowPopup(false)}} evaluations={mockData}/>
            <Fab aria-label="edit" onClick={()=>{setShowPopup(true)}} className="absolute bottom-10 right-10">
                <EditIcon color="primary" />
            </Fab>
            <div className="flex flex-col space-y-5">
                <p className="flex justify-center text-3xl text-neutral-300 pb-5">{student.name}&apos;s attention</p>
                <div className="flex flex-row pb-10">
                    <Grid container spacing={10}>
                        <Grid item>
                            <MetricsCard title="this month" attention={student.attentionMonth}/>
                        </Grid>
                        <Grid item>
                            <MetricsCard title="this week" attention={student.attentionWeek}/>
                        </Grid>
                        <Grid item>
                            <MetricsCard title="today" attention={student.attentionToday}/>
                        </Grid>
                    </Grid>
                </div>
                <div className="flex items-center space-x-20">
                    <div className="w-full">
                        <LinearChart
                            title={"This year"}
                            labels={student.attentionYearLabels}
                            userData={student.attentionYearData}
                            yAxisText="attention"
                            xAxisText="time"
                            datasetLabel="Students paying attention"
                            stepSize={1}
                        />
                    </div>
                </div>
            </div>
        </main>
    );
}

// export async function getServerSideProps(context) {
//     const { id, name } = context.query;
//
//     const month = await getStudentEvaluation(id, 6);
//     const week =  await getStudentEvaluation(id, 1);
//     const day =  await getStudentEvaluation(id, 0);
//
//     return {
//         props: {
//             student: {
//                 name: name,
//                 id: id,
//                 attentionMonth: month,
//                 attentionWeek: week,
//                 attentionToday: day,
//                 attentionYearLabels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
//                 attentionYearData: [9, 4, 6, 7, 8, 5, 3, 4, 6, 7],
//             },
//         }
//     }
//     // return {
//     //     notFound: true,
//     // }
// }