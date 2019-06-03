//
//  PlayersViewControllerTableViewController.swift
//  PlayerGameRatings
//
//  Created by Friedl, Luke on 9/25/18.
//  Copyright © 2018 CVTC. All rights reserved.
//

import UIKit

class PlayersViewControllerTableViewController: UITableViewController {

    //MARK: - Properties
    var players = SampleData.genteratePlayersData()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }


    
    
    
}

// MARK: - UITableViewDataSource
extension PlayersViewControllerTableViewController {
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return players.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "PlayerCell", for: indexPath) as! PlayerCellTableViewCell
        
        let player = players[indexPath.row]
        cell.player = player
        return cell
    }
    
}
