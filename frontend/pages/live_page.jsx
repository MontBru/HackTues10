import { Rubik } from "next/font/google";
import Card from "@/components/Card";
import Grid from "@mui/material/Grid";
import ListElement from "@/components/ListElement";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Live_page()
{
    return (
        <div className="bg-neutral-900 px-10 space-y-10 pb-20">
        <main
            className={`flex  flex-col items-center ${rubik.className}`}
        >
            <div className="flex flex-col space-y-2 items-center justify-center bg-neutral-600 rounded-lg w-100 h-26 shadow-lg p-4 m-4">
                <text className="flex justify-center text-4xl text-neutral-300">English</text>
                <p className="text-2xl text-neutral-300">9:30 - 10:50</p>
            </div>
        </main>
            <text className="flex text-4xl ">No interest:</text>
            <div className="flex flex-row items-center justify-center">
                <Grid container spacing={10}>
                    <Grid item>
                        <Card title={"Nikola Petrov"} attention={"attention: 3"}/>
                    </Grid>
                    <Grid item>
                        <Card title={"Ivan Postolov"} attention={"attention: 3"}/>
                    </Grid>
                </Grid>
            </div>
        <div>
            <text className="text-4xl">Students:</text>
            <div className="m-2 p-2">
                 <ListElement  title={"Ivan Postolov"} subclass={"10 V"}></ListElement>
                 <ListElement  title={"Bryan Monticelli"} subclass={"12 V"}></ListElement>
                 <ListElement  title={"Nikola Petrov"} subclass={"11 V"}></ListElement>
            </div>
        </div>
        </div>
    );
}