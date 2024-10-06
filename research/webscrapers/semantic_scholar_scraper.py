import requests
import json
from time import sleep
from datetime import datetime

def search_semantic_scholar(query, year, offset=0, limit=100):
    url = "http://api.semanticscholar.org/graph/v1/paper/search"
    params = {
        "query": query,
        "year": year,
        "offset": offset,
        "limit": limit,
        "fields": "title,authors,year,venue,externalIds,url"
    }
    response = requests.get(url, params=params)
    return response.json()

def convert_to_bibtex(item):
    entry_type = "article"
    key = item.get('paperId', '').replace('/', '_')

    bibtex = f"@{entry_type}{{{key},\n"
    
    if 'title' in item:
        bibtex += f"  title = {{{item['title']}}},\n"
    
    if 'authors' in item:
        authors = " and ".join([f"{a.get('name', '')}" for a in item['authors']])
        bibtex += f"  author = {{{authors}}},\n"
    
    if 'year' in item:
        bibtex += f"  year = {{{item['year']}}},\n"
    
    if 'venue' in item:
        bibtex += f"  journal = {{{item['venue']}}},\n"
    
    if 'externalIds' in item and 'DOI' in item['externalIds']:
        bibtex += f"  doi = {{{item['externalIds']['DOI']}}},\n"
    
    if 'url' in item:
        bibtex += f"  url = {{{item['url']}}},\n"
    
    bibtex += "}\n\n"
    return bibtex

def main():
    query = input("Enter your search query: ")
    year = int(input("Enter the year to search: "))
    max_results = int(input("Enter maximum number of results (or 0 for all): ") or 0)

    all_results = []
    offset = 0
    limit = 100  # Semantic Scholar allows up to 100 results per request

    while True:
        print(f"Fetching results {offset+1} to {offset+limit}...")
        results = search_semantic_scholar(query, year, offset, limit)
        
        if 'data' not in results:
            print("Error in API response:")
            print(json.dumps(results, indent=2))
            break

        all_results.extend(results['data'])
        print(f"Fetched {len(results['data'])} results")

        if len(results['data']) < limit or (max_results and len(all_results) >= max_results):
            break

        offset += limit
        sleep(1)  # Be nice to the API

    # Trim results to max_results if specified
    if max_results and len(all_results) > max_results:
        all_results = all_results[:max_results]

    print(f"\nTotal results fetched: {len(all_results)}")

    # Convert to BibTeX and save
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    filename = f'semantic_scholar_results_{timestamp}.bib'
    with open(filename, 'w', encoding='utf-8') as f:
        for item in all_results:
            f.write(convert_to_bibtex(item))

    print(f"BibTeX entries saved to '{filename}'")

if __name__ == "__main__":
    main()