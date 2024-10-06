import re
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.options import Options
from selenium.common.exceptions import TimeoutException, NoSuchElementException, WebDriverException
import time
import random

# Change parameters here
search_query = """("mutual understanding" and "generative AI" and ("Human-AI Teaming" OR "Human-AI Collaboration" OR "Human-Machine Teaming" OR "Human-AI Interaction" OR "Human-In-The-Loop"  OR "Human-AI Partnership" OR "Human-AI Communication" OR "Human-AI Systems" OR "Human-AI Cooperation" OR "Human-AI Synergy" OR "Human-Centered AI" OR "Human-AI Coordination" OR "Human-autonomy teaming" OR "human-automation teaming" OR "human-agent teaming" OR "human-machine interaction" OR "synthetic teammate"))"""
start_page = 1  # Start from this page (1-indexed)
end_page = 18   # End at this page (inclusive)

def create_bibtex_entry(title, authors, year, venue):
    key = f"{authors[0].split()[-1]}{year}" if authors else f"Unknown{year}"
    entry = f"@article{{{key},\n"
    entry += f"  title = {{{title}}},\n"
    entry += f"  author = {{{' and '.join(authors)}}},\n" if authors else "  author = {Unknown},\n"
    entry += f"  year = {{{year}}},\n"
    entry += f"  journal = {{{venue}}}\n" if venue else "  journal = {Unknown}\n"
    entry += "}\n\n"
    return entry

chrome_options = Options()
chrome_options.add_argument("--window-size=1920,1080")
chrome_options.add_argument("--start-maximized")
chrome_options.add_argument("--disable-extensions")
chrome_options.add_argument("--disable-gpu")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")

driver = webdriver.Chrome(options=chrome_options)

def wait_for_element(driver, by, value, timeout=30):
    return WebDriverWait(driver, timeout).until(EC.presence_of_element_located((by, value)))

bibtex_entries = []
problematic_entries = []

try:
    for page in range(start_page, end_page + 1):
        start_index = (page - 1) * 10
        search_url = f"https://scholar.google.com/scholar?start={start_index}&q={search_query.replace(' ', '+')}&hl=en&as_sdt=0,5&as_ylo=2023"
        driver.get(search_url)
        
        if page == start_page:
            print(f"Opened URL: {search_url}")
            print("If you see a CAPTCHA, please solve it.")
            input("Press Enter to continue when you're ready...")
        
        print(f"Processing page {page}")
        try:
            # Wait longer for results to load
            WebDriverWait(driver, 30).until(EC.presence_of_all_elements_located((By.CLASS_NAME, 'gs_r')))
            results = driver.find_elements(By.CLASS_NAME, 'gs_r')
            print(f"Found {len(results)} results on this page")
            
            for result in results:
                try:
                    title_elem = wait_for_element(result, By.CLASS_NAME, 'gs_rt')
                    title = title_elem.text.strip()
                    
                    info_elem = wait_for_element(result, By.CLASS_NAME, 'gs_a')
                    info_text = info_elem.text
                    
                    match = re.match(r'(.+?)\s*-\s*(.+),\s*(\d{4})\s*-\s*(.+)', info_text)
                    if match:
                        authors = [author.strip() for author in match.group(1).split(',')]
                        year = match.group(3)
                        venue = match.group(4).split('-')[0].strip()
                    else:
                        parts = info_text.split(' - ')
                        authors = [parts[0].strip()] if parts else []
                        year = re.search(r'\d{4}', info_text).group() if re.search(r'\d{4}', info_text) else "Unknown"
                        venue = parts[-1].strip() if len(parts) > 1 else ""
                    
                    bibtex_entry = create_bibtex_entry(title, authors, year, venue)
                    bibtex_entries.append(bibtex_entry)
                    print(f"Added entry: {title}")
                except Exception as e:
                    print(f"Error processing a result: {str(e)}")
                    print(f"Problematic entry: {result.text}")
                    problematic_entries.append(result.text)
            
            # Random wait time between 5 and 10 seconds before next page
            time.sleep(random.uniform(5, 10))
        except TimeoutException:
            print(f"Timeout on page {page}. Retrying...")
            driver.refresh()
            time.sleep(10)
        except WebDriverException as e:
            print(f"WebDriver error on page {page}: {str(e)}. Retrying...")
            time.sleep(15)
            driver.refresh()
        except Exception as e:
            print(f"Unexpected error on page {page}: {str(e)}")
            continue  # Continue to the next page instead of breaking
finally:
    driver.quit()
    print("Browser closed")

output_file = "references.bib"
with open(output_file, 'w', encoding='utf-8') as f:
    for entry in bibtex_entries:
        f.write(entry)

print(f"Created {len(bibtex_entries)} BibTeX entries in {output_file}")

if problematic_entries:
    print("Problematic entries:")
    for entry in problematic_entries:
        print(entry)
        print("---")
    print(f"Total problematic entries: {len(problematic_entries)}")