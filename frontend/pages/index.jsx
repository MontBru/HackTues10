import { Rubik } from "next/font/google";
import Navbar from "@/components/Navbar";
import LinearChart from "@/components/LinearChart";
import DatePickerField from "@/components/DatePickerField";
import BarChart from "@/components/BarChart";

const rubik = Rubik({ subsets: ["cyrillic"] });

export default function Home() {
  return (
    <main
      className={`flex min-h-screen flex-col items-center bg-neutral-900 px-10 space-y-10 pb-20 ${rubik.className}`}
    >
      <Navbar/>
      {/*<MetricsCard title="this month" attention="8/10"/>*/}
      <div className="flex flex-col items-center justify-between w-full space-y-10 sm:px-80">
        <DatePickerField/>
        <LinearChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[100, 75, 65, 90, 110, 130, 60]}/>
        <BarChart title={""} labels={['English', "Maths", "VOT", "IOT", "Biology", "Chemistry", "History"]} userData={[5, 8, 6, 9, 7, 4, 5]}/>
      </div>
    </main>
  );
}
