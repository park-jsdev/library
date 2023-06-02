import time

# Timer
start_time = time.time()

print('Runtime: ', time.time()-start_time, 'seconds.')

# To print estimated completion time within script
def est_completion_time(start_time, OBSERVED_COMPLETION_TIME):
    current_time = time.time()-start_time
    est_completion_rate = current_time/OBSERVED_COMPLETION_TIME

    if est_completion_time <= 99.9:
        res = str("%.2f" % (current_time/OBSERVED_COMPLETION_TIME)) + "%"
    else:
        res = "99.99"
    return res