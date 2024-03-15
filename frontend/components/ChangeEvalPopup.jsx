import React from 'react';
import { useState } from 'react';

const ChangeEvalPopup = ({ isOpen, onClose, evaluations }) => {
    const [evaluationsSt, setEvaluationsSt] = useState(evaluations);
  
    console.log(evaluationsSt)
    return (
    <>
      {isOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center">
          <div className="absolute inset-0 bg-gray-800 opacity-75"></div>
          <div className="relative p-8 bg-neutral-800 rounded-lg shadow-lg">
            <button
              className="absolute top-0 right-0 m-4 text-white hover:text-gray-800"
              onClick={
                ()=>{
                    //send update to backend
                    onClose();
                }
                
            }
            >
              X
            </button>
            <h2 className="text-xl text-white font-semibold mb-4">Change Evaluations</h2>
            <section>
                <ul>
                    {evaluationsSt.map((evaluation, index)=>{
                        return (
                            <li key={index} className='flex flex-row justify-between'>
                                <p className='text-white'>{evaluation.subjectName}</p>
                                <input className='text-white p-1 w-12 text-center rounded-md bg-neutral-800 outline-none border border-neutral-400 focus:border-white' value={evaluation.attention} onChange={(e)=>{
                                    e.preventDefault();
                                    const newAttention = e.target.value;
                                    const updatedEvaluations = evaluationsSt.map((evaluation, idx) => {
                                        if (idx === index) {
                                            return { ...evaluation, attention: newAttention };
                                        }
                                        return evaluation;
                                    });
                                    setEvaluationsSt(updatedEvaluations);
                                }}/>
                            </li>
                        );
                    })}
                </ul>
            </section>
            </div>
        </div>
      )}
    </>
  );
};

export default ChangeEvalPopup;