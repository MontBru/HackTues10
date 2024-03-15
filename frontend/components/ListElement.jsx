export default function ListElement({title,subclass})
{
    return(
        <div className="flex flex-col space-y-2 items-center justify-center bg-neutral-500 rounded-lg w-1/2 h-26 shadow-lg m-2">
            <p className="text-4xl text-neutral-300">{title}</p>
            <p className="text-4xl text-neutral-300">{subclass}</p>
        </div>
    );
}