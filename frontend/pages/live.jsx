import Card from "@/components/Card";
import Grid from "@mui/material/Grid";
import Navbar from "@/components/Navbar";
import ItemsList from "@/components/ItemsList";
import {useEffect, useState} from "react";
import { getLiveUserAttention } from "@/services/User/LiveUserAttention";

export default function Live() {
    const [students, setStudents] = useState([]);
    const [studentsNoInterest, setStudentsNoInterest] = useState([]);
    const [clas, setClas] = useState("12 v")

    useEffect(() => {
        const fetchData = async () => {
            setStudents(["Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V", "Nikola Petrov 12V", "Ivan Postolov 12V", "Bryan Monticelli 12V", "Stefan Georgiev 11V", "Kaloyan Sotirov 12V"]);
            const [klas, grade] = clas.split(" ");
            const NOinterestedUsers = await getLiveUserAttention(klas, grade)
            NOinterestedUsers.filter((user)=>{
                return user.evaluation !== null && user.evaluation !== undefined;
            })
            console.log(NOinterestedUsers)
            setStudentsNoInterest(NOinterestedUsers);
        }

        const interval = setInterval(() => {
            fetchData();
        }, 5000); //timeout is in miliseconds

        fetchData();
        return () => clearInterval(interval);
    }, [])

    return (
        <main className="min-h-screen bg-neutral-900 px-10 space-y-10 pb-20">
            <Navbar isStudent={false} islive={true}/>
            <div className={`flex flex-col items-center`}>
                <div className="flex items-center justify-center">
                    <p className="flex justify-center text-4xl text-neutral-300">Current lesson</p>
                </div>
            </div>
            <div className="flex justify-center space-x-20">
                <div className="flex flex-col space-y-5">
                    <p className="text-2xl text-neutral-300">Students:</p>
                    <ItemsList list={students}/>
                </div>
                <div className="space-y-5">
                    <p className="text-2xl text-neutral-300">No interest({studentsNoInterest.length}):</p>
                    <div>
                   {
                     studentsNoInterest != null &&
                        <Grid container columns={2} spacing={5} style={{width: '720px'}}>
                           
                            {
                            
                                studentsNoInterest.map((student, index) => (
                                    <Grid key={`grid-item-${index}`} item xs={1}>
                                        <Card title={student.name} attention={student.evaluation}/>
                                    </Grid>
                                ))
                            }
                        </Grid>
                   } </div>
                </div>
            </div>
        </main>
    );
}