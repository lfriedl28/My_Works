//
//  NoMagViewController.swift
//  LootRoll
//
//  Created by Friedl, Luke on 10/7/18.
//  Copyright © 2018 cvtc.edu. All rights reserved.
//

// Imports
import UIKit
import AVFoundation

class NoMagViewController: UIViewController {

    // Outlets

    @IBOutlet weak var itemLabel: UILabel!
    @IBOutlet weak var weaponSwitch: UISwitch!
    @IBOutlet weak var armorSwitch: UISwitch!
    
    // Variables
    var rollMusic: AVAudioPlayer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // Function to test for the shake gesture of the phone (Isn't core motion in this format)
    override func motionEnded(_ motion: UIEventSubtype, with event: UIEvent?) {
        if(event?.subtype == UIEventSubtype.motionShake) {
            checkItemSelected();
        }
    }
    
    // Function to check the switches on the storyboard as well as adding the items to the array to be rolled and rolls the item randomly.
    // Also calls the function that plays the dice-rolling sound effect on shake.
    func checkItemSelected() {
        var itemsToBeRolled = [""]
        let weaponsArray = ["Dagger", "Longsword", "Rapier", "Club", "Battleaxe", "Greatsword", "Flail", "Greataxe", "Bow", "Crossbow", "Halberd", "Spear"]
        let armorArray = ["Leather", "Studded leather", "Breastplate", "Chainmail", "Half-Plate", "Full Plate", "Buckler", "Shield"]
        if armorSwitch.isOn && weaponSwitch.isOn {
            itemsToBeRolled.removeAll()
            itemsToBeRolled.append(contentsOf: weaponsArray)
            itemsToBeRolled.append(contentsOf: armorArray)
        } else if weaponSwitch.isOn {
            itemsToBeRolled.append(contentsOf: weaponsArray)
            itemsToBeRolled.remove(at: 0)
        } else if armorSwitch.isOn {
            itemsToBeRolled.append(contentsOf: armorArray)
            itemsToBeRolled.remove(at: 0)
        } else {
            itemsToBeRolled.removeAll()
            itemsToBeRolled.append("Please select one")
            itemsToBeRolled.append("UUUUGGGGHHHH")
        }
        
        let randomRoll = itemsToBeRolled[Int(arc4random_uniform(UInt32(itemsToBeRolled.count)))]
        itemLabel.text = randomRoll
        playMusic()
    }
    
    // Function that finds the roll.wav file and plays it when called in the checkSwitches function
    func playMusic(){
        let path = Bundle.main.path(forResource: "roll.wav", ofType:nil)!
        let url = URL(fileURLWithPath: path)
        
        do {
            rollMusic = try AVAudioPlayer(contentsOf: url)
            rollMusic?.play()
        } catch {
            print("Couldn't load audio file")
        }
    }
}
