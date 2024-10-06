import requests
import json
from time import sleep
from datetime import datetime

def search_crossref(query, from_date, until_date, offset=0, rows=20, user_agent=""):
    url = "https://api.crossref.org/works"
    params = {
        "query": query,
        "filter": f"from-pub-date:{from_date},until-pub-date:{until_date}",
        "offset": offset,
        "rows": rows,
        "sort": "relevance",  # Sort by relevance
        "select": "DOI,title,author,published,container-title,abstract"
    }
    headers = {
        "User-Agent": user_agent
    }
    response = requests.get(url, params=params, headers=headers)
    return response.json()

def convert_to_bibtex(item):
    entry_type = item.get('type', 'article')
    doi = item.get('DOI', '')
    key = doi.replace('/', '_') if doi else f"item_{item.get('id', '')}"

    bibtex = f"@{entry_type}{{{key},\n"
    
    if 'title' in item and item['title']:
        bibtex += f"  title = {{{item['title'][0]}}},\n"
    
    if 'author' in item:
        authors = " and ".join([f"{a.get('family', '')}, {a.get('given', '')}" for a in item['author']])
        bibtex += f"  author = {{{authors}}},\n"
    
    if 'published' in item:
        year = item['published']['date-parts'][0][0]
        bibtex += f"  year = {{{year}}},\n"
    
    if 'container-title' in item and item['container-title']:
        bibtex += f"  journal = {{{item['container-title'][0]}}},\n"
    
    if doi:
        bibtex += f"  doi = {{{doi}}},\n"
    
    if 'abstract' in item:
        bibtex += f"  abstract = {{{item['abstract']}}},\n"
    
    bibtex += "}\n\n"
    return bibtex

def main():
    query = input("Enter your search query: ")
    start_year = int(input("Enter start year: "))
    end_year = int(input("Enter end year: "))
    max_results = int(input("Enter maximum number of results (or 0 for all): ") or 0)
    user_agent = input("Enter your User-Agent string (press Enter for default): ") or "MySearchScript/1.0 (https://example.org; mailto:your@email.com)"

    from_date = f"{start_year}-01-01"
    until_date = f"{end_year}-12-31"

    all_results = []
    offset = 0
    rows = 100  # Number of results per request

    while True:
        print(f"Fetching results {offset+1} to {offset+rows}...")
        results = search_crossref(query, from_date, until_date, offset, rows, user_agent)
        
        if 'message' not in results or 'items' not in results['message']:
            print("Error in API response:")
            print(json.dumps(results, indent=2))
            break

        items = results['message']['items']
        all_results.extend(items)
        print(f"Fetched {len(items)} results")

        if len(items) < rows or (max_results and len(all_results) >= max_results):
            break

        offset += rows
        sleep(1)  # Be nice to the API

    # Trim results to max_results if specified
    if max_results and len(all_results) > max_results:
        all_results = all_results[:max_results]

    print(f"\nTotal results fetched: {len(all_results)}")

    # Convert to BibTeX and save
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    filename = f'crossref_results_{timestamp}.bib'
    with open(filename, 'w', encoding='utf-8') as f:
        for item in all_results:
            f.write(convert_to_bibtex(item))

    print(f"BibTeX entries saved to '{filename}'")

if __name__ == "__main__":
    main()