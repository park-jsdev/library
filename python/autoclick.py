import time
import pyautogui

def keep_awake(interval=10):
    try:
        while True:
            pyautogui.click()
            print("Mouse clicked to keep the system awake.")
            time.sleep(interval)
    except KeyboardInterrupt:
        print("Script terminated by user.")

if __name__ == "__main__":
    keep_awake(10)
