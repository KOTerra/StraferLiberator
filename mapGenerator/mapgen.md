# Map Generator


----------

Un tool ce ne ajuta sa automatizam generarea de obiecte ce sunt adaugate in PlayWorld, cat si matricele asociate pathfindingului in fiecare WorldSection

Fiecare WorldSection a fost construit multi-layered in **Tiled** cu tile-uri de 64*64pixeli prezente ca tileset-uri ïn "mapGenerator\TiledMaps\tilesets". Acest lucru ne permite sa modificam foarte usor harta si ceea ce se afla pe ea dintr-un GUI. Apoi acestea sunt exportate  in format JSON in folderul "mapGenerator/TiledMaps". Cand aplicatia este rulata, un dialog windows pentru selectie de fisiere este deschis penttru a alege ce fisier vrem sa parsam in consola avem 2 optiuni disponibile:

1. Pentru a genera matricea de pathfinding
1. pentru a genera cod java cu functii init() ce poate fi inserat direct in clasele WorldSection<11-23>

Output-ul este salvat in fisiere .txt, iar apoi adaugate in core project.

Aplicatia a fost scrisa in C++17 , iar parsarea s-a realizat cu libraria Tileson

