import numpy as np

def generate_synthetic_data(num_samples):
    avg_bpm_min_val = np.random.randint(55, 95, size=num_samples)
    avg_bpm_max_val_diff = np.random.randint(5, 20, size=num_samples)
    avg_bpm_max_val = avg_bpm_min_val + avg_bpm_max_val_diff

    bpm_values = np.random.randint(60, 181, size=num_samples)
    
    concentration_labels = np.zeros(num_samples)
    
    for i, bpm in enumerate(bpm_values):
        if bpm_values[i] >= avg_bpm_min_val[i] and bpm_values[i] <= avg_bpm_max_val[i]:
            concentration_labels[i] = np.random.randint(5,9)
        elif bpm_values[i]< avg_bpm_min_val[i]:
            concentration_labels[i] = int(max(np.random.randint(0,5) - (avg_bpm_min_val[i]-bpm_values[i])/8, 0))
        else:
            concentration_labels[i] = int(min( np.random.randint(5,9) + (bpm_values[i]-avg_bpm_max_val[i])/12, 9))
    
    synthetic_data = np.column_stack((avg_bpm_min_val, avg_bpm_max_val, bpm_values, concentration_labels))
    
    return synthetic_data

synthetic_data = generate_synthetic_data(10000)
X = synthetic_data[:, :-1]
y = synthetic_data[:, -1] 
eval_data = generate_synthetic_data(3000)
X_eval = eval_data[:, :-1]
y_eval = eval_data[:, -1] 